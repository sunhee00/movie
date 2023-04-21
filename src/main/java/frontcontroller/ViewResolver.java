package frontcontroller;

public class ViewResolver {
   public static String makeView(String nextPage) {
      return "WEB-INF/qna/" + nextPage +".jsp";
   }
}