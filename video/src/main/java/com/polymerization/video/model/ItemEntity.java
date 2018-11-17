package com.polymerization.video.model;

public  class ItemEntity implements Cloneable {
    
        public String url;
        public String thumb;
        public String title;

        @Override
        public ItemEntity clone() throws CloneNotSupportedException {
            return (ItemEntity) super.clone();
        }
    }