package com.lll.zk02_lx;

import java.util.List;

public class GoodsList {

    private List<MlssBean> mlss;
    private List<PzshBean> pzsh;
    private List<RxxpBean> rxxp;

    public List<MlssBean> getMlss() {
        return mlss;
    }

    public void setMlss(List<MlssBean> mlss) {
        this.mlss = mlss;
    }

    public List<PzshBean> getPzsh() {
        return pzsh;
    }

    public void setPzsh(List<PzshBean> pzsh) {
        this.pzsh = pzsh;
    }

    public List<RxxpBean> getRxxp() {
        return rxxp;
    }

    public void setRxxp(List<RxxpBean> rxxp) {
        this.rxxp = rxxp;
    }

    public static class MlssBean {
        /**
         * commodityList : [{"commodityId":32,"commodityName":"唐狮女鞋冬季女鞋休闲鞋子女士女鞋百搭帆布鞋女士休闲鞋子女款板鞋休闲女鞋帆布鞋","masterPic":"http://172.17.8.100/images/small/commodity/nx/fbx/1/1.jpg","price":88,"saleNum":0},{"commodityId":16,"commodityName":"Lara style清洗剂","masterPic":"http://172.17.8.100/images/small/commodity/mzhf/mzgj/6/1.jpg","price":12,"saleNum":0}]
         * id : 1003
         * name : 魔力时尚
         */

        private int id;
        private String name;
        private List<CommodityListBean> commodityList;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<CommodityListBean> getCommodityList() {
            return commodityList;
        }

        public void setCommodityList(List<CommodityListBean> commodityList) {
            this.commodityList = commodityList;
        }

        public static class CommodityListBean {
            /**
             * commodityId : 32
             * commodityName : 唐狮女鞋冬季女鞋休闲鞋子女士女鞋百搭帆布鞋女士休闲鞋子女款板鞋休闲女鞋帆布鞋
             * masterPic : http://172.17.8.100/images/small/commodity/nx/fbx/1/1.jpg
             * price : 88
             * saleNum : 0
             */

            private int commodityId;
            private String commodityName;
            private String masterPic;
            private int price;
            private int saleNum;

            public int getCommodityId() {
                return commodityId;
            }

            public void setCommodityId(int commodityId) {
                this.commodityId = commodityId;
            }

            public String getCommodityName() {
                return commodityName;
            }

            public void setCommodityName(String commodityName) {
                this.commodityName = commodityName;
            }

            public String getMasterPic() {
                return masterPic;
            }

            public void setMasterPic(String masterPic) {
                this.masterPic = masterPic;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getSaleNum() {
                return saleNum;
            }

            public void setSaleNum(int saleNum) {
                this.saleNum = saleNum;
            }
        }
    }

    public static class PzshBean {
        /**
         * commodityList : [{"commodityId":6,"commodityName":"轻柔系自然裸妆假睫毛","masterPic":"http://172.17.8.100/images/small/commodity/mzhf/cz/4/1.jpg","price":39,"saleNum":0},{"commodityId":13,"commodityName":"贝览得美妆蛋","masterPic":"http://172.17.8.100/images/small/commodity/mzhf/mzgj/3/1.jpg","price":44,"saleNum":0},{"commodityId":11,"commodityName":"盒装葫芦粉扑美妆蛋化妆海绵","masterPic":"http://172.17.8.100/images/small/commodity/mzhf/mzgj/1/1.jpg","price":9,"saleNum":0},{"commodityId":5,"commodityName":"双头两用修容笔","masterPic":"http://172.17.8.100/images/small/commodity/mzhf/cz/3/1.jpg","price":39,"saleNum":156}]
         * id : 1004
         * name : 品质生活
         */

        private int id;
        private String name;
        private List<CommodityListBeanX> commodityList;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<CommodityListBeanX> getCommodityList() {
            return commodityList;
        }

        public void setCommodityList(List<CommodityListBeanX> commodityList) {
            this.commodityList = commodityList;
        }

        public static class CommodityListBeanX {
            /**
             * commodityId : 6
             * commodityName : 轻柔系自然裸妆假睫毛
             * masterPic : http://172.17.8.100/images/small/commodity/mzhf/cz/4/1.jpg
             * price : 39
             * saleNum : 0
             */

            private int commodityId;
            private String commodityName;
            private String masterPic;
            private int price;
            private int saleNum;

            public int getCommodityId() {
                return commodityId;
            }

            public void setCommodityId(int commodityId) {
                this.commodityId = commodityId;
            }

            public String getCommodityName() {
                return commodityName;
            }

            public void setCommodityName(String commodityName) {
                this.commodityName = commodityName;
            }

            public String getMasterPic() {
                return masterPic;
            }

            public void setMasterPic(String masterPic) {
                this.masterPic = masterPic;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getSaleNum() {
                return saleNum;
            }

            public void setSaleNum(int saleNum) {
                this.saleNum = saleNum;
            }
        }
    }

    public static class RxxpBean {
        /**
         * commodityList : [{"commodityId":25,"commodityName":"秋冬季真皮兔毛女鞋韩版休闲平底毛毛鞋软底百搭浅口水钻加绒棉鞋毛毛鞋潮鞋","masterPic":"http://172.17.8.100/images/small/commodity/nx/ddx/1/1.jpg","price":158,"saleNum":0},{"commodityId":19,"commodityName":"环球 时尚拼色街拍百搭小白鞋 韩版原宿ulzzang板鞋 女休闲鞋","masterPic":"http://172.17.8.100/images/small/commodity/nx/bx/2/1.jpg","price":78,"saleNum":0},{"commodityId":29,"commodityName":"秋冬新款平底毛毛豆豆鞋加绒单鞋一脚蹬懒人鞋休闲","masterPic":"http://172.17.8.100/images/small/commodity/nx/ddx/5/1.jpg","price":278,"saleNum":0}]
         * id : 1002
         * name : 热销新品
         */

        private int id;
        private String name;
        private List<CommodityListBeanXX> commodityList;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<CommodityListBeanXX> getCommodityList() {
            return commodityList;
        }

        public void setCommodityList(List<CommodityListBeanXX> commodityList) {
            this.commodityList = commodityList;
        }

        public static class CommodityListBeanXX {
            /**
             * commodityId : 25
             * commodityName : 秋冬季真皮兔毛女鞋韩版休闲平底毛毛鞋软底百搭浅口水钻加绒棉鞋毛毛鞋潮鞋
             * masterPic : http://172.17.8.100/images/small/commodity/nx/ddx/1/1.jpg
             * price : 158
             * saleNum : 0
             */

            private int commodityId;
            private String commodityName;
            private String masterPic;
            private int price;
            private int saleNum;

            public int getCommodityId() {
                return commodityId;
            }

            public void setCommodityId(int commodityId) {
                this.commodityId = commodityId;
            }

            public String getCommodityName() {
                return commodityName;
            }

            public void setCommodityName(String commodityName) {
                this.commodityName = commodityName;
            }

            public String getMasterPic() {
                return masterPic;
            }

            public void setMasterPic(String masterPic) {
                this.masterPic = masterPic;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getSaleNum() {
                return saleNum;
            }

            public void setSaleNum(int saleNum) {
                this.saleNum = saleNum;
            }
        }
    }
}

