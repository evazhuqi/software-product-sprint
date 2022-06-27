import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.KeyFactory;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/form-handler")
public class FormHandlerServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    // Get the value entered in the form.
    String textValue = request.getParameter("text-input");

    // Print the value so you can see it in the server logs.
    System.out.println("You submitted: " + textValue);

    // Write the value to the response so the user can see it.
    response.getWriter().println("Thank You!! I will email you ASAP:)) ");

    String title = Jsoup.clean(request.getParameter("title"), Safelist.none());
    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    KeyFactory keyFactory = datastore.newKeyFactory().setKind("Task");
    FullEntity taskEntity =
      Entity.newBuilder(keyFactory.newKey())
            .build();
  
    datastore.put(taskEntity);


    response.sendRedirect("https://qzhu-sps-summer22.appspot.com/");
    
  }
}