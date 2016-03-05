package testtesting.testing;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private RequestQueue requestQueue;
    private Student student;
    private ArrayList<Student> students = new ArrayList<>();

    private XmlPullParserFactory factory;
    private XmlPullParser parser;
    private final String URL = "http://192.168.1.71:89/tutorial4/testing.php";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        requestQueue = Volley.newRequestQueue(this);

        try {
            factory = XmlPullParserFactory.newInstance();
            parser = factory.newPullParser();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }


        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final StringRequest stringRequest = new StringRequest(URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            parser.setInput(new StringReader(response));
                            int event = parser.getEventType();

                            String tag = "", val = "";

                            while (event != XmlPullParser.END_DOCUMENT) {

                                tag = parser.getName();
                                switch (event) {
                                    case XmlPullParser.START_TAG:
                                        if (tag.equals("student")) {
                                            student = new Student();
                                            students.add(student);
                                        }
                                        break;

                                    case XmlPullParser.TEXT:
                                        val = parser.getText();
                                        break;

                                    case XmlPullParser.END_TAG:

                                        switch (tag) {
                                            case "id":
                                                student.setId(Integer.parseInt(val));
                                                break;
                                            case "first_name":
                                                student.setFirst_name(val);
                                                break;

                                            case "last_name":
                                                student.setLast_name(val);
                                                break;
                                            case "age":
                                                student.setAge(Integer.parseInt(val));
                                                break;
                                            default:
                                                break;
                                        }
                                        break;
                                }

                                event = parser.next();
                            }


                        } catch (XmlPullParserException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        for (int i = 0; i < students.size(); i++) {
                            students.get(i).print_student();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

                requestQueue.add(stringRequest);

            }
        });

    }


}
