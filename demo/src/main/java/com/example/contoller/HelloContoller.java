package com.example.contoller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.example.entity.*;
import com.example.service.UserService;
import com.example.service.dao.CtwingInfoDao;
import com.example.util.RedisUtil;
import io.netty.buffer.UnpooledByteBufAllocator;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import org.springframework.core.io.buffer.NettyDataBufferFactory;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
/*import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;*/
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

@RestController
public class HelloContoller {

    //private final DataSource dataSource;

    private final UserService userService;

    private final RedisUtil redisUtil;

    private final CtwingInfoDao ctwingInfoDao;

    private ObjectMapper objectMapper;

    @Autowired
    private Author author;


    @Autowired
    private ServerCodecConfigurer serverCodecConfigurer = null;

    /*public HelloContoller(@Qualifier("dataSource") DataSource dataSource, UserService userService) {
        this.dataSource = dataSource;
        this.userService = userService;
    }*/

    public HelloContoller(UserService userService, RedisUtil redisUtil, CtwingInfoDao ctwingInfoDao) {
        this.userService = userService;
        this.redisUtil = redisUtil;
        this.ctwingInfoDao = ctwingInfoDao;
    }
    private org.slf4j.Logger logger = LoggerFactory.getLogger(HelloContoller.class);



    @PostMapping("/demo")
    public Mono<Void> connect(final ServerHttpRequest request, final ServerHttpResponse response) {
        //logger.info("connect...");
        final ResolvableType reqDataType = ResolvableType.forClass(byte[].class);
        return response.writeWith(serverCodecConfigurer.getReaders().stream()
                .filter(reader -> reader.canRead(reqDataType, MediaType.ALL))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No Data"))
                .readMono(reqDataType, request, Collections.emptyMap())
                .cast(byte[].class)
                .map(bytes -> {
                    try {
                        CtwingInfo ctwingInfo = new CtwingInfo();
                        final String reqBody = new String(bytes, StandardCharsets.UTF_8);
                        //log.info("reqBody => \n {}", reqBody);
                        JSONObject jsonObject = JSON.parseObject(reqBody.toString());
                        Map map = new HashMap();
                        map.put("deviceId", jsonObject.get("deviceId").toString());
                        List<CtwingInfo> list = ctwingInfoDao.select("getCtwingInfoList",map,"readonly");
                        if("dataReport".equals(jsonObject.get("messageType"))){
                            ctwingInfo.setImei(jsonObject.get("IMEI").toString());
                            //ctwingInfo.setImsi(jsonObject.get("IMSI").toString());
                            //ctwingInfo.setDeviceType(jsonObject.get("deviceType").toString());
                            //ctwingInfo.setAssocAssetid(jsonObject.get("assocAssetId").toString());
                            ctwingInfo.setUpDataSn((Integer) jsonObject.get("upPacketSN"));
                            ctwingInfo.setUpPacketSn((Integer) jsonObject.get("upDataSN"));
                            JSONObject payload = JSON.parseObject(jsonObject.get("payload").toString());
                            if((Integer)jsonObject.get("serviceId")==2){
                                SignalReport params = JSON.parseObject(jsonObject.get("payload").toString(), new TypeReference<SignalReport>() {});
                                ctwingInfo.setCellId(params.getCell_id());
                                ctwingInfo.setEcl(params.getEcl());
                                ctwingInfo.setPci(params.getPci());
                                ctwingInfo.setRsrp(params.getRsrp());
                                ctwingInfo.setSinr(params.getSinr());
                                System.out.println(params.toString());

                            }else if ((Integer)jsonObject.get("serviceId")==6){
                                GpsReport params = JSON.parseObject(jsonObject.get("payload").toString(), new TypeReference<GpsReport>() {});
                                ctwingInfo.setLatitude(params.getLatitude());
                                ctwingInfo.setLongitude(params.getLongitude());
                                ctwingInfo.setAltitude(params.getAltitude());

                                String addresss =  getAdd(params.getLongitude()+","+params.getLatitude());
                                String json = addresss.substring(addresss.indexOf("(")+1,addresss.lastIndexOf(")"));
                                JSONObject jsonObject1 = JSONObject.parseObject(json);
                                jsonObject1 = JSONObject.parseObject(jsonObject1.getString("regeocode"));
                                //System.out.println(jsonObject.getString("formatted_address"));
                                ctwingInfo.setAddresss(jsonObject1.getString("formatted_address"));

                                System.out.println(params.toString());

                            }else if ((Integer)jsonObject.get("serviceId")==7){
                                Integer battery_vol =  (Integer) payload.get("battery_vol");
                                ctwingInfo.setBatteryVol(battery_vol);
                                System.out.println(battery_vol);

                            }else if ((Integer)jsonObject.get("serviceId")==8){
                                String wifi_mac =  payload.get("wifi_mac").toString();
                                ctwingInfo.setWifiMac(wifi_mac);
                                System.out.println(wifi_mac);

                            }else if ((Integer)jsonObject.get("serviceId")==9){
                                Integer sleep_time =  (Integer) payload.get("sleep_time");
                                String wakeup_time =  payload.get("wakeup_time").toString();
                                ctwingInfo.setSleepTime(sleep_time);
                                ctwingInfo.setWakeupTime(wakeup_time);
                                System.out.println(sleep_time);
                                System.out.println(wakeup_time);
                            }

                        }
                        if(list.size()>0){
                            ctwingInfo.setId(list.get(0).getId());
                            ctwingInfo.setUpdateTime(new Date());
                            ctwingInfoDao.updateByPrimaryKeySelective(ctwingInfo);
                        }else{
                            ctwingInfo.setDeviceId(jsonObject.get("deviceId").toString());
                            ctwingInfo.setTenantId(jsonObject.get("tenantId").toString());
                            ctwingInfo.setProductId(jsonObject.get("productId").toString());
                            ctwingInfo.setProtocol(jsonObject.get("protocol").toString());
                            ctwingInfo.setCreateTime(new Date());
                            ctwingInfo.setUpdateTime(new Date());
                            ctwingInfo.setIsDelete(0);
                            ctwingInfoDao.insertSelective(ctwingInfo);
                        }
                        //System.out.println(reqBody);
                        ///TODO:  实现自己的业务

                        final NettyDataBufferFactory nettyDataBufferFactory = new NettyDataBufferFactory(new UnpooledByteBufAllocator(false));
                        return nettyDataBufferFactory.wrap("200".getBytes(StandardCharsets.UTF_8));
                    } catch (Throwable ex) {
                        //log.warn("connect-exp: {}", ex.getMessage());
                        return null;
                    }
                })
        );
    }
   /* @RequestMapping("/command")
    public String command(){
        AepDeviceCommandClient client = AepDeviceCommandClient.newClient()
                .appKey("Your app key here").appSecret("Your app secret here")
                .build();

        CreateCommandRequest request = new CreateCommandRequest();
// set your request params here
// request.setParamMasterKey(MasterKey);	// single value
// request.addParamMasterKey(MasterKey);	// or multi values
// request.setBody([BINARY DATA])
        System.out.println(client.CreateCommand(request));

// more requests

        client.shutdown();
        return "";
    }*/


    @RequestMapping("/hello")
    public String hello(){
        //反序列化
        //objectMapper.readValue();
        //序列化
        //objectMapper.writeValueAsString();
        //Objects.equals();
        //StringUtils.delimitedListToStringArray();
        /*Connection connection = null;
        PreparedStatement preparedStatement =null;
        try {
            connection = dataSource.getConnection();
            String sql ="insert into users (name) VALUES(?);";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"dengqiu");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }*/

        return "名字123345345：" + author.getName() + " 地址：" + author.getCsdnUrl();

    }


    /**
     * 根据坐标获取具体地址
     * @param coor 坐标字符串
     * @return
     */
    public static String getAdd(String coor){
        String urlString = "http://restapi.amap.com/v3/geocode/regeo?key=8325164e247e15eea68b59e89200988b&s=rsv3&location="+coor+"&radius=2800&callback=jsonp_452865_&platform=JS&logversion=2.0&sdkversion=1.3&appname=http%3A%2F%2Flbs.amap.com%2Fconsole%2Fshow%2Fpicker&csid=49851531-2AE3-4A3B-A8C8-675A69BCA316";
        String res = "";
        try {
            URL url = new URL(urlString);
            java.net.HttpURLConnection conn = (java.net.HttpURLConnection)url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(conn.getInputStream(),"UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                res += line+"\n";
            }
            in.close();
        } catch (Exception e) {
            System.out.println("error in wapaction,and e is " + e.getMessage());
        }
        System.out.println(res);
        return res;
    }



    /*@PostMapping("/demo")
    public String  requestTask(HttpServletRequest request, HttpServletResponse response) {
        BufferedReader reader = null;
        StringBuilder data = new StringBuilder();
        String line = null;
        String result = "";
        try {
            reader = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
            while ((line = reader.readLine()) != null) {
                data.append(line);
            }
            reader.close();
            //JSONObject jsonObject = JSON.parseObject(data.toString());
            System.out.println(data.toString());
            //Bitcoin bitcoin =  JsonTest.getBitcoin(data.toString());

            //bitcoinFwService.save(data.toString());
            //bitcoinMinerService.update(data.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "200";
    }*/
}
