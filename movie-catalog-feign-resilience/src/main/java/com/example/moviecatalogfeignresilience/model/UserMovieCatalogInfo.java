package com.example.moviecatalogfeignresilience.model;

import java.util.List;

public class UserMovieCatalogInfo {
   private String id;
   private List<CatalogItem> catalogItems;

   public UserMovieCatalogInfo(){

   }

   public UserMovieCatalogInfo (String id, List<CatalogItem> catalogItems) {
      this.id = id;
      this.catalogItems = catalogItems;
   }

   public String getId () {
      return id;
   }

   public void setId (String id) {
      this.id = id;
   }

   public List<CatalogItem> getCatalogItems () {
      return catalogItems;
   }

   public void setCatalogItems (List<CatalogItem> catalogItems) {
      this.catalogItems = catalogItems;
   }
}
