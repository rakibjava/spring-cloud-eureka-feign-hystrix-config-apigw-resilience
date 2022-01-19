package com.example.limitservice;

public class Limit {
   private String name;
   private String min;
   private String max;

   Limit(String name,String min, String max){
      this.min=min;
      this.max=max;
      this.name=name;
   }

   public String getMin () {
      return min;
   }

   public void setMin (String min) {
      this.min = min;
   }

   public String getMax () {
      return max;
   }

   public void setMax (String max) {
      this.max = max;
   }

   public String getName () {
      return name;
   }

   public void setName (String name) {
      this.name = name;
   }
}
