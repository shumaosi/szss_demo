package com.szss.androidapp.bean;

/**
 * Created by wuwei on 2017/5/18.
 */

public class ItemBean {

    public int getItemType() {
        return 0;
    }

    public static class ImageBean extends ItemBean {
        public int imageRes;

        public ImageBean(int imageRes) {
            this.imageRes = imageRes;
        }

        @Override
        public int getItemType() {
            return 1;
        }
    }

    public static class TitleBean extends ItemBean {
        public String title;

        public TitleBean(String title) {
            this.title = title;
        }

        @Override
        public int getItemType() {
            return 3;
        }
    }

	public static class ImageClickItemBean extends ItemBean {
        public int imageRes1;
		public int imageRes2;
		public int imageRes3;
		public int imageRes4;
		
	 public ImageClickItemBean(int imageRes1, int imageRes2, int imageRes3,int imageRes4) {
            this.imageRes1 = imageRes1;
            this.imageRes2 = imageRes2;
            this.imageRes3 = imageRes3;
			this.imageRes4 = imageRes4;
        }

        @Override
        public int getItemType() {
            return 2;
        }
	}
	
    public static class DetailBean extends ItemBean {
        public int imageRes;
        public String title;
        public String price;

        public DetailBean(int imageRes, String title, String price) {
            this.imageRes = imageRes;
            this.title = title;
            this.price = price;
        }

        @Override
        public int getItemType() {
            return 4;
        }
    }

    public static class TopicBean extends ItemBean {
        public int imageRes;
        public String title;
        public String content;

        public TopicBean(int imageRes, String title, String content) {
            this.imageRes = imageRes;
            this.title = title;
            this.content = content;
        }

        @Override
        public int getItemType() {
            return 5;
        }
    }

}
