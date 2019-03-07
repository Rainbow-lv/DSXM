package com.lll.yuekao_moni.bean;

import java.util.List;

public class LeftBean {

    /**
     * cid : 2
     * list : [{"icon":"http://120.27.23.105/images/icon.png","name":"手机","pcid":6,"pscid":35},{"icon":"http://120.27.23.105/images/icon.png","name":"笔记本","pcid":6,"pscid":36},{"icon":"http://120.27.23.105/images/icon.png","name":"平板电脑","pcid":6,"pscid":37},{"icon":"http://120.27.23.105/images/icon.png","name":"游戏机","pcid":6,"pscid":38},{"icon":"http://120.27.23.105/images/icon.png","name":"摄影摄像","pcid":6,"pscid":39}]
     * name : 数码家电
     * pcid : 6
     */

    private String cid;
    private String name;
    private String pcid;
    private List<ListBean> list;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPcid() {
        return pcid;
    }

    public void setPcid(String pcid) {
        this.pcid = pcid;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * icon : http://120.27.23.105/images/icon.png
         * name : 手机
         * pcid : 6
         * pscid : 35
         */

        private String icon;
        private String name;
        private int pcid;
        private int pscid;

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPcid() {
            return pcid;
        }

        public void setPcid(int pcid) {
            this.pcid = pcid;
        }

        public int getPscid() {
            return pscid;
        }

        public void setPscid(int pscid) {
            this.pscid = pscid;
        }
    }
}

