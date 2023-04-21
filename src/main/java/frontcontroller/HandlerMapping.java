package frontcontroller;

import java.util.HashMap;

import qna.*;

public class HandlerMapping {
   private HashMap<String, Controller> mappings;
   public HandlerMapping() {
	  System.out.println("handlerMapping 실행중");
      mappings = new HashMap<String, Controller>();
      System.out.println("맵핑이름"+mappings);
      mappings.put("/QNADeleteController.do", new QNADeleteController());
      mappings.put("/QNAInfoController.do", new QNAInfoController());
      mappings.put("/QNAListController.do", new QNAListController());
      mappings.put("/QNAReWriteController.do", new QNAReWriteController());
      mappings.put("/QNAReWriteProController.do", new QNAReWritepProController());
      mappings.put("/QNAUpdateController.do", new QNAUpdateController());
      mappings.put("/QNAUpdateProController.do", new QNAUpdateProController());
      mappings.put("/QNAWriteProController.do", new QNAWriteProController());
      mappings.put("/QNAWriteController.do", new QNAWriteController());
   }
     public Controller getController(String key) { // key=>/memberList.do
    	 System.out.println("key이름"+key);
    	 return mappings.get(key);
     }
}