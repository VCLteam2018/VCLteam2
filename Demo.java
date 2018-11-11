package samples;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

class ProdcutOutOfStockDTO {
   private String description;
   private String shoesID;
   private Map<Integer, String> product;

   public ProdcutOutOfStockDTO(String description, String shoesID, TreeMap<Integer, String> product) {
      this.description = description;
      this.shoesID = shoesID;
      this.product = product;
   }
   
   public List<String> getList() {
      return product.entrySet().stream()
              .map(e -> String.format("%s;%s-%s", shoesID, e.getKey(), e.getValue()))
              .collect(Collectors.toList());
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public String getShoesID() {
      return shoesID;
   }

   public void setShoesID(String shoesID) {
      this.shoesID = shoesID;
   }

   public Map<Integer, String> getProduct() {
      return product;
   }

   public void setProduct(Map<Integer, String> product) {
      this.product = product;
   }
}

public class Demo {

   public static void main(String[] args) {
      TreeMap<Integer, String> map1 = new TreeMap<>();
      map1.put(36, "America");
      map1.put(37, "America");
      map1.put(38, "America");
      TreeMap<Integer, String> map2 = new TreeMap<>();
      map2.put(36, "Korean");
      map2.put(37, "Korean");
      map2.put(38, "Korean");
      TreeMap<Integer, String> map3 = new TreeMap<>();
      map3.put(36, "France");
      map3.put(37, "France");
      map3.put(38, "France");
      TreeMap<Integer, String> map4 = new TreeMap<>();
      map4.put(37, "France");
      map4.put(38, "France");
      map4.put(36, "France");
      List<ProdcutOutOfStockDTO> list = Arrays.asList(new ProdcutOutOfStockDTO("USA", "SA31", map1),
              new ProdcutOutOfStockDTO("RUS", "RU21", map2),
              new ProdcutOutOfStockDTO("FRA", "FR21", map3),
              new ProdcutOutOfStockDTO("FRA", "FR31", map4)
      );
      
      Map<List<String>, String> result = list.stream()
              .collect(Collectors.toMap(ProdcutOutOfStockDTO::getList, ProdcutOutOfStockDTO::getDescription));
      result.entrySet().forEach(e -> {
         System.out.println("Description: " + e.getValue());
         System.out.println("Combox: ");
         e.getKey().forEach(s -> System.out.println(s.substring(s.indexOf(";") + 1) + " "));
         System.out.println("---------------------------------------------");
      });
   }
}
