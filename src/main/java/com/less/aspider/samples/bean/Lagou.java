package com.less.aspider.samples.bean;

import java.util.List;

/**
 * Created by deeper on 2018/2/6.
 */

public class Lagou {

    private ContentBean content;
    private String message;
    private int state;

    public ContentBean getContent() {
        return content;
    }

    public void setContent(ContentBean content) {
        this.content = content;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public static class ContentBean {

        private DataBean data;
        private List<?> rows;

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public List<?> getRows() {
            return rows;
        }

        public void setRows(List<?> rows) {
            this.rows = rows;
        }

        public static class DataBean {
            /**
             * page : {"pageSize":15,"start":"0","result":[{"logger":{"traceCapable":true,"name":"com.lagou.entity.mobile.MobilePosition"},"positionId":2080797,"positionName":"Java开发","city":"上海","createTime":"今天 10:32","salary":"15k-20k","companyId":24995,"companyLogo":"image1/M00/00/33/CgYXBlTUXI-AC08_AACIkHlny3Y866.jpg","companyName":"泛微","companyFullName":"上海泛微网络科技股份有限公司"},{"logger":{"traceCapable":true,"name":"com.lagou.entity.mobile.MobilePosition"},"positionId":4109292,"positionName":"JAVA开发","city":"北京","createTime":"今天 18:01","salary":"15k-30k","companyId":139755,"companyLogo":"i/image2/M00/01/B5/CgoB5lm_Z2eAO7g_AAAsw5Ww4Ig707.jpg","companyName":"京东商城","companyFullName":"北京京东世纪贸易有限公司"},{"logger":{"traceCapable":true,"name":"com.lagou.entity.mobile.MobilePosition"},"positionId":4099853,"positionName":"Java","city":"深圳","createTime":"2018-02-01","salary":"15k-30k","companyId":40459,"companyLogo":"i/image2/M00/1B/63/CgotOVoCv-eAPNQcAARRTfkzqqo936.png","companyName":"SenseTime","companyFullName":"北京市商汤科技开发有限公司"},{"logger":{"traceCapable":true,"name":"com.lagou.entity.mobile.MobilePosition"},"positionId":4108576,"positionName":"JAVA开发工程师","city":"深圳","createTime":"今天 16:06","salary":"9k-11k","companyId":16831,"companyLogo":"i/image/M00/02/AB/CgqKkVaXX_6AaLKaAAAX52Kvjjg750.jpg","companyName":"武汉佰钧成技术有限公司","companyFullName":"武汉佰钧成技术有限责任公司"},{"logger":{"traceCapable":true,"name":"com.lagou.entity.mobile.MobilePosition"},"positionId":4108139,"positionName":"java开发工程师","city":"南京","createTime":"今天 14:53","salary":"3k-5k","companyId":233304,"companyLogo":"i/image/M00/51/49/CgpEMlly8hSAbcA2AABQfzNbLIM888.png","companyName":"燃网络","companyFullName":"南京网燃网络科技有限公司"},{"logger":{"traceCapable":true,"name":"com.lagou.entity.mobile.MobilePosition"},"positionId":4108707,"positionName":"Java开发工程师","city":"北京","createTime":"今天 16:28","salary":"15k-30k","companyId":3786,"companyLogo":"i/image/M00/67/87/CgpFT1mpHKCAF5oKAABeE95QxFk132.png","companyName":"宜信","companyFullName":"普信恒业科技发展（北京）有限公司"},{"logger":{"traceCapable":true,"name":"com.lagou.entity.mobile.MobilePosition"},"positionId":4108433,"positionName":"JAVA","city":"北京","createTime":"今天 15:37","salary":"25k-50k","companyId":50702,"companyLogo":"i/image/M00/6A/05/Cgp3O1gW8zSAUwUsAABMptH-XY087.jpeg","companyName":"美团点评","companyFullName":"北京三快在线科技有限公司"},{"logger":{"traceCapable":true,"name":"com.lagou.entity.mobile.MobilePosition"},"positionId":4107696,"positionName":"java工程师","city":"武汉","createTime":"今天 14:44","salary":"10k-20k","companyId":96107,"companyLogo":"i/image/M00/75/FB/CgpFT1pDLQGAKLwcAAAXkDBDEl0247.png","companyName":"昊沧","companyFullName":"上海昊沧系统控制技术有限责任公司"},{"logger":{"traceCapable":true,"name":"com.lagou.entity.mobile.MobilePosition"},"positionId":4108205,"positionName":"Java开发工程师","city":"北京","createTime":"今天 15:00","salary":"15k-20k","companyId":6664,"companyLogo":"i/image/M00/33/60/CgqKkVdROZyAYbaLAAAxkPjGw9s876.jpg","companyName":"竞彩258","companyFullName":"北京明致鸿丰彩体育科技股份有限公司"},{"logger":{"traceCapable":true,"name":"com.lagou.entity.mobile.MobilePosition"},"positionId":4107052,"positionName":"java开发工程师","city":"杭州","createTime":"今天 10:51","salary":"20k-40k","companyId":153849,"companyLogo":"i/image/M00/1F/54/CgpFT1kRuMmASL74AAAg3WZnNI005.jpeg","companyName":"蚂蚁金服集团","companyFullName":"支付宝(杭州)信息技术有限公司"},{"logger":{"traceCapable":true,"name":"com.lagou.entity.mobile.MobilePosition"},"positionId":2814041,"positionName":"JAVA工程师","city":"大连","createTime":"今天 18:09","salary":"8k-10k","companyId":53260,"companyLogo":"i/image/M00/34/30/CgqKkVdU-EmACreAAAAXnWssTiA345.jpg","companyName":"桔子分期","companyFullName":"北京桔子分期电子商务有限公司"},{"logger":{"traceCapable":true,"name":"com.lagou.entity.mobile.MobilePosition"},"positionId":4106612,"positionName":"Java开发工程师","city":"苏州","createTime":"今天 09:07","salary":"8k-15k","companyId":8583,"companyLogo":"i/image/M00/BB/AF/Cgp3O1jHZ36AEfthAABPVJClfSg213.png","companyName":"瑞翼","companyFullName":"苏州瑞翼信息技术有限公司"},{"logger":{"traceCapable":true,"name":"com.lagou.entity.mobile.MobilePosition"},"positionId":4107338,"positionName":"JAVA","city":"上海","createTime":"今天 11:30","salary":"15k-30k","companyId":14229,"companyLogo":"i/image/M00/32/FE/Cgp3O1dQFiaAelOPAAF1O_dbrx8557.jpg","companyName":"微盟","companyFullName":"上海萌店信息科技有限公司"},{"logger":{"traceCapable":true,"name":"com.lagou.entity.mobile.MobilePosition"},"positionId":4106921,"positionName":"java开发工程师","city":"上海","createTime":"今天 10:34","salary":"18k-25k","companyId":13301,"companyLogo":"i/image/M00/02/1C/CgqKkVaCTcOABoZEAAAV8VpLs8k705.jpg","companyName":"杉德集团","companyFullName":"上海久彰电子商务有限公司"},{"logger":{"traceCapable":true,"name":"com.lagou.entity.mobile.MobilePosition"},"positionId":3453025,"positionName":"java开发工程师","city":"北京","createTime":"今天 18:06","salary":"15k-30k","companyId":24561,"companyLogo":"i/image/M00/31/91/CgpEMlk1KXiAF-KGAAASfBkZDSg185.jpg","companyName":"国美互联网","companyFullName":"国美在线电子商务有限公司"}],"totalCount":"16270","pageNo":1}
             * custom : {"positionName":"java","city":"全国"}
             */

            private PageBean page;
            private CustomBean custom;

            public PageBean getPage() {
                return page;
            }

            public void setPage(PageBean page) {
                this.page = page;
            }

            public CustomBean getCustom() {
                return custom;
            }

            public void setCustom(CustomBean custom) {
                this.custom = custom;
            }

            public static class PageBean {

                private int pageSize;
                private String start;
                private String totalCount;
                private int pageNo;
                private List<ResultBean> result;

                public int getPageSize() {
                    return pageSize;
                }

                public void setPageSize(int pageSize) {
                    this.pageSize = pageSize;
                }

                public String getStart() {
                    return start;
                }

                public void setStart(String start) {
                    this.start = start;
                }

                public String getTotalCount() {
                    return totalCount;
                }

                public void setTotalCount(String totalCount) {
                    this.totalCount = totalCount;
                }

                public int getPageNo() {
                    return pageNo;
                }

                public void setPageNo(int pageNo) {
                    this.pageNo = pageNo;
                }

                public List<ResultBean> getResult() {
                    return result;
                }

                public void setResult(List<ResultBean> result) {
                    this.result = result;
                }

                public static class ResultBean {
                    /**
                     * logger : {"traceCapable":true,"name":"com.lagou.entity.mobile.MobilePosition"}
                     * positionId : 2080797
                     * positionName : Java开发
                     * city : 上海
                     * createTime : 今天 10:32
                     * salary : 15k-20k
                     * companyId : 24995
                     * companyLogo : image1/M00/00/33/CgYXBlTUXI-AC08_AACIkHlny3Y866.jpg
                     * companyName : 泛微
                     * companyFullName : 上海泛微网络科技股份有限公司
                     */

                    private LoggerBean logger;
                    private int positionId;
                    private String positionName;
                    private String city;
                    private String createTime;
                    private String salary;
                    private int companyId;
                    private String companyLogo;
                    private String companyName;
                    private String companyFullName;

                    public LoggerBean getLogger() {
                        return logger;
                    }

                    public void setLogger(LoggerBean logger) {
                        this.logger = logger;
                    }

                    public int getPositionId() {
                        return positionId;
                    }

                    public void setPositionId(int positionId) {
                        this.positionId = positionId;
                    }

                    public String getPositionName() {
                        return positionName;
                    }

                    public void setPositionName(String positionName) {
                        this.positionName = positionName;
                    }

                    public String getCity() {
                        return city;
                    }

                    public void setCity(String city) {
                        this.city = city;
                    }

                    public String getCreateTime() {
                        return createTime;
                    }

                    public void setCreateTime(String createTime) {
                        this.createTime = createTime;
                    }

                    public String getSalary() {
                        return salary;
                    }

                    public void setSalary(String salary) {
                        this.salary = salary;
                    }

                    public int getCompanyId() {
                        return companyId;
                    }

                    public void setCompanyId(int companyId) {
                        this.companyId = companyId;
                    }

                    public String getCompanyLogo() {
                        return companyLogo;
                    }

                    public void setCompanyLogo(String companyLogo) {
                        this.companyLogo = companyLogo;
                    }

                    public String getCompanyName() {
                        return companyName;
                    }

                    public void setCompanyName(String companyName) {
                        this.companyName = companyName;
                    }

                    public String getCompanyFullName() {
                        return companyFullName;
                    }

                    public void setCompanyFullName(String companyFullName) {
                        this.companyFullName = companyFullName;
                    }

                    public static class LoggerBean {
                        /**
                         * traceCapable : true
                         * name : com.lagou.entity.mobile.MobilePosition
                         */

                        private boolean traceCapable;
                        private String name;

                        public boolean isTraceCapable() {
                            return traceCapable;
                        }

                        public void setTraceCapable(boolean traceCapable) {
                            this.traceCapable = traceCapable;
                        }

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }
                    }
                }
            }

            public static class CustomBean {

                private String positionName;
                private String city;

                public String getPositionName() {
                    return positionName;
                }

                public void setPositionName(String positionName) {
                    this.positionName = positionName;
                }

                public String getCity() {
                    return city;
                }

                public void setCity(String city) {
                    this.city = city;
                }
            }
        }
    }
}
