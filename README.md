# ASpider

## 使用介绍

构建一个可运行在Android平台的爬虫框架，目前仍在完善。

特性：
1. 轻量级注解建表存储（类似Hibernate），c3p0连接池
2. 网页解析：Jsoup、Xpath、正则
3. 代理请求（普通代理池）
4. QueueScheduler、PriorityScheduler、BDBScheduler等方式的调度器
5. 支持GET、POST类型的爬虫
6. 请求参数伪造，逻辑稍复杂的爬虫可传递参数。
7. 内置下载器：HTTPConnection、Okhttp（可定制）

下面是一个爬取全网链接的基础爬虫案例：

```
public class SimpleSpider {

    public static String TAG = SimpleSpider.class.getSimpleName();

    public static final String REGEX_URL = "(http|https)+://[^\\s|\\?|&|'|\"]+(com|cn|org|net)+?";

    public static final Pattern pattern = Pattern.compile(REGEX_URL);

    public static void start(String startUrl) {
        ASpider.create()
                .pageProcessor(new PageProcessor() {
                    @Override
                    public void process(Page page) {
						// 输出项(默认ConsolePipeline输出到终端)
						page.putField("html", page.getRawText());
						// 匹配网页链接
						String newUrl = RegexUtils.get(REGEX_URL).selectSingle(html, 0);
						// 添加新链接到队列
						page.addTargetRequestsNoReferer(newUrl);
                    }
                })
                .thread(20)
                .urls(startUrl)
                .runAsync();
    }

    public static void main(String[] args) {
        start("http://www.xx.com");
    }
}
```

项目封装了一个方便存储到MySQL的工具类，只需继承自DatabaseHelper：

步骤一：编写实体Bean

```
@Table
public class JavaPdf {

    @Column(value = "id", columnDefinition = "int primary key auto_increment")
    private int id;

    @Column
    private String url;

    @Column
    private String title;

    @Column(value = "panUrl", columnDefinition = "varchar(255) not null unique")
    private String panUrl;

    @Column(value = "passwd")
    private String passwd;

    public JavaPdf(String title, String panUrl, String passwd, String url) {
        this.title = title;
        this.panUrl = panUrl;
        this.passwd = passwd;
        this.url = url;
    }
}
```

步骤二：数据库建表

```
JavaPdfSpider javaPdfSpider = new JavaPdfSpider();
javaPdfSpider.createTable(JavaPdf.class);
```

步骤三：存储数据

```
javaPdfSpider.insert(new JavaPdf(title, panUrl, passwd, page.getUrl()));
```

希望详细了解的请看源码，仅此粗略介绍！

## 协议
GPL（暂定）