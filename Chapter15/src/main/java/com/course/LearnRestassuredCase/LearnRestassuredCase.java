package com.course.LearnRestassuredCase;

import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.*;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.matcher.ResponseAwareMatcherComposer;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matcher;
import org.testng.annotations.Test;

import javax.xml.namespace.NamespaceContext;
import java.io.File;
import java.math.BigDecimal;
import java.util.*;

import static com.github.fge.jsonschema.SchemaVersion.DRAFTV3;
import static com.github.fge.jsonschema.SchemaVersion.DRAFTV4;
import static io.restassured.RestAssured.*;
import static io.restassured.config.EncoderConfig.encoderConfig;
import static io.restassured.config.JsonConfig.jsonConfig;
import static io.restassured.config.XmlConfig.xmlConfig;
import static io.restassured.matcher.RestAssuredMatchers.endsWithPath;
import static io.restassured.matcher.RestAssuredMatchers.matchesDtd;
import static io.restassured.matcher.RestAssuredMatchers.matchesXsd;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.module.jsv.JsonSchemaValidatorSettings.settings;
import static io.restassured.path.json.config.JsonPathConfig.NumberReturnType.BIG_DECIMAL;
import static io.restassured.path.xml.XmlPath.from;
import static io.restassured.path.json.JsonPath.from;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class LearnRestassuredCase {

    @Test
    public void testone(){
        given()
                .when()
                .get("http://localhost:8889/lotto")
                .then()
                .body("lotto.lottoId",equalTo(5));

    }

    @Test
    public void testtwo(){
        given()
                .when()
                .get("http://localhost:8889/lotto")
                .then()
                .body("lotto.winners.winnerId",hasItems(23,54));

    }
    @Test
    public void testthree(){
        given()
                .when()
                .get("http://localhost:8889/price")
                .then()
                .body("price",is(12.12f));

    }

    @Test
    public void testfour(){
        given()
                .config(RestAssured.config().jsonConfig(jsonConfig().numberReturnType(BIG_DECIMAL)))
                .when()
                .get("http://localhost:8889/price")
                .then()
                .body("price",is(new BigDecimal(12.122222)));

    }

    @Test
    public void testfive(){
        given()
                .when()
                .get("http://localhost:8889/lotto")
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("tempfiles/products-schema.json"));

    }

    @Test
    public void testsix(){
        // Given
        JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.newBuilder().setValidationConfiguration(ValidationConfiguration.newBuilder().setDefaultVersion(DRAFTV4).freeze()).freeze();
        // When
        get("http://localhost:8889/lotto").then().assertThat().body(matchesJsonSchemaInClasspath("tempfiles/products-schema.json").using(jsonSchemaFactory));


    }

    @Test
    public void testsenven(){
        get("http://localhost:8889/lotto").then().assertThat().body(matchesJsonSchemaInClasspath("tempfiles/products-schema.json").using(settings().with().checkedValidation(false)));
    }

    @Test
    public void testeight(){
        JsonSchemaValidator.settings = settings().with().jsonSchemaFactory(
                JsonSchemaFactory.newBuilder().setValidationConfiguration(ValidationConfiguration.newBuilder().setDefaultVersion(DRAFTV3).freeze()).freeze()).
                and().with().checkedValidation(false);

        get("http://localhost:8889/lotto").then().assertThat().body(matchesJsonSchemaInClasspath("tempfiles/products-schema.json"));
    }

    @Test
    public void testnine(){

        String json = "{\n" +
                "        \"lotto\":{\n" +
                "          \"lottoId\":5,\n" +
                "          \"winning-numbers\":[2,45,34,23,7,5,3],\n" +
                "          \"winners\":[{\n" +
                "            \"winnerId\":23,\n" +
                "            \"numbers\":[2,45,34,23,3,5]\n" +
                "          },{\n" +
                "            \"winnerId\":54,\n" +
                "            \"numbers\":[52,3,12,11,18,22]\n" +
                "          }]\n" +
                "        }\n" +
                "      }";
        assertThat(json,matchesJsonSchemaInClasspath("tempfiles/products-schema.json"));
    }

    @Test
    public void testten(){
        given()
                .when()
                .get("http://localhost:8889/json")
                .then()
                .body("$",hasItems(1,2,3));

    }

    @Test
    public void testeleven(){


        //post入参为form 表单参数，返回类型为xml
        given()
                .proxy(8888)//连接代理
                .formParam("firstName", "John")
                .formParam("lastName","Doe")
                .when()
                .post("http://localhost:8889/getxml")
                .then()
                .using()
                .defaultParser(Parser.XML)//返回类型是xml格式
                .body("greeting.firstName",equalTo("John"))
                .body("greeting.lastName",equalTo("Doe"));//校验结果


        with().formParams("firstName", "John", "lastName", "Doe").when().post("http://localhost:8889/getxml").then().body("greeting.firstName", equalTo("John"), "greeting.lastName", equalTo("Doe"));
        with().formParams("firstName", "John", "lastName", "Doe").when().post("http://localhost:8889/getxml").getBody().prettyPrint();




        Map<String,Object> map = new HashMap();
        map.put("firstName","John");
        map.put("lastName","Doe");

        List<String> values = new ArrayList<String>();
        values.add("John");



        //post入参为json格式参数,只能用body入参json类型的参数，不能用param、formparam等
        given()
//                .contentType("application/json")  不需要配置，已经认为是json格式入参
                .proxy(8888)
//                .body("{\"firstName\":\"John\"}")
                .body(map)
//                .param("firstName", values)
                .when()
                .post("http://localhost:8889/getxmlwithjson")
                .getBody().prettyPrint();


    }

    @Test
    public void testtwelve(){
        given().
                config(RestAssured.config().xmlConfig(xmlConfig().declareNamespace("test", "http://localhost/"))).
                when().
                get("http://localhost:8889/getxmlwithnamespace").
                then().
                body("foo.bar.text()", equalTo("sudo make me a sandwich!")).
                body(":foo.:bar.text()", equalTo("sudo ")).
                body("foo.test:bar.text()", equalTo("make me a sandwich!"));
    }

    @Test
    public void testthirteen(){
        Map map = new HashMap();
        map.put("firstName","John");
        map.put("lastName","Doe");


        given()
                .proxy(8888)
                .body(map)
                .when()
                .post("http://localhost:8889/getxmlwithjson")
                .then()
                .body(hasXPath("/greeting/firstName[text()='John']"));

        given()
                .proxy(8888)
                .body(map)
                .when()
                .post("http://localhost:8889/getxmlwithjson")
//                .getBody().prettyPrint();
                .then()
                .body(hasXPath("/greeting/firstName",containsString("Jo")));

    }
    @Test
    public void testfourteen(){

        NamespaceContext namespaceContext = new NamespaceContext() {
            @Override
            public String getNamespaceURI(String prefix) {
                return null;
            }

            @Override
            public String getPrefix(String namespaceURI) {
                return null;
            }

            @Override
            public Iterator getPrefixes(String namespaceURI) {
                return null;
            }
        };


        given()
                .config(RestAssured.config().xmlConfig(xmlConfig().with().namespaceAware(true)))
                .proxy(8888)
                .when()
                .get("http://localhost:8889/getxmlwithnamespacetwo")
                .then()
                .body(hasXPath("/table",namespaceContext,equalTo("111")));



    }

    @Test
    public void testfifteen(){

//        File file = new File("D:\\Springboot\\AutoTest\\Chapter15\\src\\main\\resources\\XSD\\assertxsd.xsd");
//
//        given()
//                .proxy(8888)
//                .when()
//                .get("http://localhost:8889/getresponsewithfile")
//                .then()
//                .assertThat()
//                .body(matchesXsd(file));//接口返回内容是xml，需要把xml转换成xml schema，然后生成一个文件，把文件传过来作为参数


        File file1 = new File("D:\\Springboot\\AutoTest\\Chapter15\\src\\main\\resources\\XSD\\mybatis-3-config.dtd");
        given()
                .proxy(8888)
                .when()
                .get("http://localhost:8889/getresponsewithDTDfile")
                .then()
                .assertThat()
                .body(matchesDtd(file1));//需要传DTD的文件的地址

    }
    @Test
    public void testsixteen(){
        given()
                .proxy(8888)
                .get("http://localhost:8889/getfindall")
//                .getBody().prettyPrint();
                .then()
                .body("p2pdata.data.words.findAll{it.length()>4}",hasItems("buffalo","dinosaur"));
    }

    @Test
    public void testseventeen(){
        given()
                .proxy(8888)
                .get("http://localhost:8889/getfindall")
//                .getBody().prettyPrint();
                .then()
                .body("p2pdata.data.words.find{it.length()>4}",hasItems("buffalo"));
    }

    @Test
    public void testeighteen(){
//        given()
//                .proxy(8888)
//                .get("http://localhost:8889/getfindall")
////                .getBody().prettyPrint();
//                .then()
//                .body("p2pdata.data.words.collect{it.length()}.sum()",greaterThan(50));

        given()
                .proxy(8888)
                .get("http://localhost:8889/getfindall")
//                .getBody().prettyPrint();
                .then()
                .body("p2pdata.data.words.collect{it.length()}.min()",greaterThan(50));
//        .body("p2pdata.data.words.collect{it.length()}.max()",greaterThan(50));
    }

    @Test
    public void testnineteen(){
        given()
                .proxy(8888)
                .get("http://localhost:8889/assertxmlresponse")
                .then()
                .body("shopping.category.find { it.@type == 'groceries' }.item", hasItems("Chocolate", "Coffee"));


        // Get the response body as a String
        String response = get("http://localhost:8889/assertxmlresponse").asString();
// And get the groceries from the response. "from" is statically imported from the XmlPath class
//        List<String> groceries = from(response).getList("shopping.category.find { it.@type == 'groceries' }.item");
        List<String> groceries = XmlPath.from(response).getList("shopping.category.find { it.@type == 'groceries' }.item");

        for (String s : groceries){
            System.out.println(s);
        }



        List<String> groceriesthree = get("http://localhost:8889/assertxmlresponse").xmlPath().getList("shopping.category.find { it.@type == 'groceries' }.item");
        for (String s : groceriesthree){
            System.out.println(s);
        }

        given()
                .proxy(8888)
                .get("http://localhost:8889/assertxmlresponse")
                .then()
                .body("**.find { it.@type == 'groceries' }", hasItems("Chocolate", "Coffee"));

    }
    @Test
    public void testtwenty(){
        given()
                .get("http://localhost:8889/assertjsonresponse")
                .then()
                .body("store.book.findAll{it.price < 10 }.title",hasItems("Sayings of the Century","Moby Dick"));


        String response = get("http://localhost:8889/assertjsonresponse").asString();
// And get all books with price < 10 from the response. "from" is statically imported from the JsonPath class
        List<String> bookTitles = JsonPath.from(response).getList("store.book.findAll { it.price < 10 }.title");
        for (String s : bookTitles){
            System.out.println(s);
        }

        given()
                .get("http://localhost:8889/assertjsonresponse")
                .then()
                .body("store.book.author.collect{it.length() }.sum()",greaterThan(50));

        given()
                .get("http://localhost:8889/assertjsonresponse")
                .then()
                .body("store.book.author*.length().sum()",greaterThan(50));


        int sumOfAllAuthorLengths = JsonPath.from(response).getInt("store.book.author*.length().sum()");
        assertThat(sumOfAllAuthorLengths,is(53));
        System.out.println(sumOfAllAuthorLengths);

    }
    @Test
    public void testtwentyone() {
        //会调失败
//        given()
//                .get("http://localhost:8889/assertfloatresponse")
//                .then()
//                .body("price", equalTo(12.12));

//        given()
//                .get("http://localhost:8889/assertfloatresponse")
//                .then()
//                .body("price", equalTo(12.12f));

//        given()
//                .expect()
//                .statusCode(400)
//                .body("price", equalTo(12.12))
//                .when()
//                .get("http://localhost:8889/assertfloatresponse");

        given()
                .when()
                .get("http://localhost:8889/assertfloatresponse")
                .then()
                .statusCode(400)
                .body("price", equalTo(12.12));

    }
    @Test
    public void testtwentytwo() {
        given().param("name","qinzhenxia").and().header("token","1234567890").when().get("http://localhost:8889/assertandrequest").then().statusCode(200).and().body("price",equalTo("12"));

        given()
                .param("name","qinzhenxia")
                .header("token","1234567890")
                .when()
                .get("http://localhost:8889/assertandrequest")
                .then()
                .statusCode(200)
                .body("price",equalTo("12"));

    }
    @Test
    public void testtwentythree() {

        String nextlink = given()
                .param("name","qinzhenxia")
                .header("token","1234567890")
                .when()
                .get("http://localhost:8889/getpath")
                .then()
                .contentType(ContentType.JSON)
                .body("title",equalTo("My Title"))
                .extract()
                .path("_links.next.href");
        System.out.println(nextlink);



        Response response = given()
                .param("name","qinzhenxia")
                .header("token","1234567890")
                .when()
                .get("http://localhost:8889/getpath")
                .then()
                .contentType(ContentType.JSON)
                .body("title",equalTo("My Title"))
                .extract()
                .response();

        String headers = response.header("token");
        String nextlinktwo = response.path("_links.next.href");

        System.out.println(headers);
        System.out.println(nextlinktwo);




        //使用jsonpath获取返回值response.asString()  一定要用asString()方法才能
        String hrefvalue = JsonPath.from(response.asString()).getString("_links.next.href");
        System.out.println("使用jsonpath获取hrefvalue:"+hrefvalue);


        //setRoot()设置根路径
        JsonPath jsonPath = new JsonPath(response.asString()).setRoot("_links");
        String hrefvaluetwo = jsonPath.getString("next.href");
        System.out.println("使用jsonpath获取hrefvaluetwo:"+hrefvaluetwo);


    }
    @Test
    public void testtwentyfour(){
        Response response = given()
                .param("name","qinzhenxia")
                .header("token","1234567890")
                .header("otherheader","1111111111")
                .when()
                .get("http://localhost:8889/getheadersAndCookies")
                .then()
//                .contentType(ContentType.JSON)
                .body("title",equalTo("My Title"))
                .extract()
                .response();

        //获取所有headers
        Headers header = response.getHeaders();
        String getheader = header.toString();
        System.out.println("获取所有的headers:"+getheader);//moco的接口只能返回一个

        System.out.println("---------------------------");
        //获取单个header
        String getoneheader = response.getHeader("token");
        String getoneheadertwo = response.getHeader("otherheader");
        System.out.println("token的值是："+getoneheader+",otherheader的值是："+getoneheadertwo);

        System.out.println("---------------------------");

        //获取多个cookie，moco的接口只能返回一个
        Map<String,String> map = response.getCookies();
        for (Map.Entry<String,String> entry : map.entrySet()){
            System.out.println("key:"+entry.getKey()+",values:"+entry.getValue());
        }

        System.out.println("---------------------------");
        String ddd = response.getCookie("xintai_ucenter_sso");
        //获取单个cookie
        System.out.println(ddd);

        //获取状态行
        System.out.println("状态行是："+response.getStatusLine());

        //获取状态码
        System.out.println("状态码是："+response.getStatusCode());

    }
    @Test
    public void testtwentyfive(){
//        given()
//                .proxy(8888)
//                .param("name","qinzhenxia")
//                .pathParam("getheadersAndCookies","getheadersAndCookies")
//                .header("token","1234567890")
//                .header("otherheader","1111111111")
//                .when()
//                .get("http://localhost:8889/{test}/{getheadersAndCookies}","testing")
//                .then()
//                .contentType(ContentType.JSON)
//                .body("title",equalTo("My Title"))
//                .extract()
//                .response();

        //把header换成cookie
        given()
                .proxy(8888)
                .param("name","qinzhenxia")
                .pathParam("test","testing")
                .cookie("token","1234567890")
                .cookie("otherheader","1111111111")
                .when()
                .get("http://localhost:8889/{test}/{getheadersAndCookies}","getheadersAndCookies")
                .then()
                .contentType(ContentType.JSON)
                .body("title",equalTo("My Title"))
                .extract()
                .response();
    }
    @Test
    public void testtwentysix(){

        //一个cookie
        Cookie cookie = new Cookie.Builder("token","1234567890").setSecured(true).setComment("get a cookie").build();

        given()
                .proxy(8888)
                .param("name","qinzhenxia")
                .pathParam("test","testing")
                .cookie(cookie)
                .when()
                .get("http://localhost:8889/{test}/{getCookies}","getCookies")
                .then()
                .body("title",equalTo("My Title"));



    }
    @Test
    public void testtwentysenven(){
        //多个cookie入参
        Cookie cookie1 = new Cookie.Builder("token","1234567890").setComment("the frist cookie").build();
        Cookie cookie2 = new Cookie.Builder("tokentwo","77777").setComment("the sencond cookie").build();

        Cookies cookie = new Cookies(cookie1,cookie2);

        given()
                .proxy(8888)
                .param("name","qinzhenxia")
                .pathParam("test","testing")
                .cookies(cookie)
                .when()
                .get("http://localhost:8889/{test}/{getmoreCookies}","getmoreCookies")
                .then()
                .body("title",equalTo("My Title"));

    }

    @Test
    public void testtwentyeight(){
        //校验返回多个cookie

        //多个cookie入参
        Cookie cookie1 = new Cookie.Builder("token","1234567890").setComment("the frist cookie").build();
        Cookie cookie2 = new Cookie.Builder("tokentwo","77777").setComment("the sencond cookie").build();

        Cookies cookie = new Cookies(cookie1,cookie2);

        given()
                .proxy(8888)
                .param("name","qinzhenxia")
                .cookies(cookie)
                .when()
                .get("http://localhost:8889/testing/getmoreCookies")
                .then()
                .assertThat()
                .cookie("xintai_ucenter_sso","UCENTER-ad78845d-0277-4384-8759-8ceee5ecaf0a")
                //还可以在验证头时使用映射函数。 例如，假设您要验证“Content-Length”头部小于1000.然后，您可以使用映射函数首先将头值转换为int，然后在使用Hamcrest验证前使用“整数” 匹配器：
                .header("Content-Length",Integer::parseInt,lessThan(100));

        //返回多个cookie
//        get("/x").then().assertThat().cookies("cookieName1", "cookieValue1", "cookieName2", "cookieValue2"). ..
//        get("/x").then().assertThat().cookies("cookieName1", "cookieValue1", "cookieName2", containsString("Value2")). .


        //校验状态码
//        get("/x").then().assertThat().statusCode(200). ..
//    get("/x").then().assertThat().statusLine("something"). ..
//    get("/x").then().assertThat().statusLine(containsString("some")). ..


        //校验header
//        get("/x").then().assertThat().header("headerName", "headerValue"). ..
//        get("/x").then().assertThat().headers("headerName1", "headerValue1", "headerName2", "headerValue2"). ..
//        get("/x").then().assertThat().headers("headerName1", "headerValue1", "headerName2", containsString("Value2")). ..


        //Content-Type
//        get("/x").then().assertThat().contentType(ContentType.JSON). ..
        //内容全匹配
//        get("/x").then().assertThat().body(equalTo("something")). ..


    }
    @Test
    public void testtwentynine(){
        //关联型验证
        //{ "userId" : "some-id", "href" : "http://localhost:8080/some-id" } ，验证“href”属性以“userId”属性的值结尾

        given().proxy(8888).get("http://localhost:8889/getuserid").then()
                .body("href", new ResponseAwareMatcher<Response>() {
                    @Override
                    public Matcher<?> matcher(Response response) throws Exception {
                        return equalTo("http://localhost:8080/"+response.path("userId"));
                    }
                });

        given()
                .proxy(8888)
                .when()
                .get("http://localhost:8889/getuserid")
                .then()
                .body("href",response -> equalTo("http://localhost:8080/"+response.path("userId") ));


        given()
                .proxy(8888)
                .when()
                .get("http://localhost:8889/getuserid")
                .then()
                .body("href",endsWithPath("userId") );


        given()
                .proxy(8888)
                .when()
                .get("http://localhost:8889/getuserid")
                .then()
                .body("href", ResponseAwareMatcherComposer.and(startsWith("http://localhost:8080/"),endsWithPath("userId")) );



    }
    @Test
    public void testthirty(){

        //对象映射成json 作为参数传参
        LoginDATA loginDATA = new LoginDATA();
        loginDATA.setUsername("秦振霞");
        loginDATA.setPassword("123456");

        given()
                .proxy(8888)
                .contentType("application/json;charset=UTF-8")
                .body(loginDATA)
                .when()
                .post("http://localhost:8889/testlogincase");

        //参数中有中文需要设置字符集
//        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig().defaultCharsetForContentType("UTF-8", "application/json"));

        given()
                .config(RestAssured.config().encoderConfig(encoderConfig().defaultCharsetForContentType("UTF-8","application/json")))
                .proxy(8888)
                .body(loginDATA, ObjectMapperType.JACKSON_2)
                .when()
                .post("http://localhost:8889/testlogincase");



        //form格式入参
        given()
                .proxy(8888)
                .contentType("application/json;charset=UTF-8")
                .formParam("username",loginDATA.getUsername())
                .formParam("password",loginDATA.getPassword())
                .when()
                .post("http://localhost:8889/testlogincaseform");

    }

    @Test
    public void testthirtyone(){
        //反序列化，返回response内容序列化为对象，可以输出对象中的属性值信息
        LoginDATA loginDATA = get("http://localhost:8889/getjavaobject").as(LoginDATA.class);
        System.out.println("username:"+loginDATA.getUsername()+",password:"+loginDATA.getPassword());


        LoginDATA loginDATAtwo = get("http://localhost:8889/getjavaxmlobject").as(LoginDATA.class);
        System.out.println("username:"+loginDATAtwo.getUsername()+",password:"+loginDATAtwo.getPassword());


        //自定义content-type，是指程序无法解析的，如果接口中返回Content-Type:application/json 则不配置parser("application/json",Parser.JSON)系统也是可以解析的
        //本接口中返回的是xml，如果返回json，用Parser.JSON就行
        LoginDATA loginDATAthree = expect().parser("application/something",Parser.XML).when().get("http://localhost:8889/getjavaxmlobject").as(LoginDATA.class);
        System.out.println("username:"+loginDATAthree.getUsername()+",password:"+loginDATAthree.getPassword());

        LoginDATA loginDATAfour = expect().defaultParser(Parser.XML).when().get("http://localhost:8889/getjavaxmlobject").as(LoginDATA.class);
        System.out.println("username:"+loginDATAfour.getUsername()+",password:"+loginDATAfour.getPassword());

        //显示的反序列化器
        LoginDATA loginDATAfive = get("http://localhost:8889/getjavaxmlobject").as(LoginDATA.class,ObjectMapperType.JAXB);
        System.out.println("username:"+loginDATAfive.getUsername()+",password:"+loginDATAfive.getPassword());
    }

    @Test
    public void testthirtytwo(){
        //模式复用 ResponseSpecBuilder返回校验模式复用  RequestSpecBuilder请求模式复用
        //入参和返回校验有重复的可以用模式复用


        //入参模式复用
        RequestSpecBuilder specBuilder = new RequestSpecBuilder();
        specBuilder.addParam("username","秦振霞");
        specBuilder.addParam("password","123456");
        specBuilder.addHeader("token","1234567890");
        specBuilder.setContentType("application/json");
        specBuilder.setProxy(8888);
        RequestSpecification specification = specBuilder.build();

        //返回body校验
        ResponseSpecBuilder specBuilder1 = new ResponseSpecBuilder();
        specBuilder1.expectStatusCode(200);
        specBuilder1.expectBody("message",containsString("hello"));
        ResponseSpecification specification1 = specBuilder1.build();


        Response response = given()
                .log().params()
                .spec(specification)
                .when()
                .post("http://localhost:8889/testspec")
                .then()
                .log().all()
                .spec(specification1)
                .extract()
                .response();
        System.out.println(response.asString());

        Response newresponse = new ResponseBuilder().clone(response).setBody("oh,my god").build();
        System.out.println(newresponse.asString());

    }
    @Test
    public void testthirtythree(){

//        //使用根路径.root("p2pdata.body")
        given()
                .proxy(8888)
                .when()
                .get("http://localhost:8889/testroot")
                .then()
                .root("p2pdata.body")
                .body("firstname",equalTo("zhenxia"));

//        //配置默认的根路径
        RestAssured.rootPath = "p2pdata.body";
        given()
                .proxy(8888)
                .when()
                .get("http://localhost:8889/testroot")
                .then()
                .body("firstname",equalTo("zhenxia"));


        //在根路径上附加一些参数

        given()
                .proxy(8888)
                .when()
                .get("http://localhost:8889/testroot")
                .then()
                .root("%s.%s",withArgs("p2pdata","body"))
                .body("firstname",equalTo("zhenxia"))
                .appendRoot("%s.%s",withArgs("family","one"))
                .body(withNoArgs(),equalTo("daddy"));//withNoArgs()方法是接着上面的appendRoot添加路径的基础上添加的


        //拆分路径
                given()
                .proxy(8888)
                .when()
                .get("http://localhost:8889/testroot")
                .then()
                .root("p2pdata.body")
                .body("firstname",equalTo("zhenxia"))
                .detachRoot("body")//拆分路径后后root路径就是p2pdata,接下来要验证的路径就是p2pdata.des
                .body("des",equalTo("happy family"));


     //路径参数

//        String someSubPath = "else";
//        int index = 1;
//        get("/x").then().body("something.%s[%d]", withArgs(someSubPath, index), equalTo("some value")). ..
        given()
                .proxy(8888)
                .when()
                .get("http://localhost:8889/testroot")
                .then()
                .root("p2pdata.body.family.%s")
                .body(withArgs("one"),equalTo("daddy"))
                .body(withArgs("two"),equalTo("mother"));

    }




    }
