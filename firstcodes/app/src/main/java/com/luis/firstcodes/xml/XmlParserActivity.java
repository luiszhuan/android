package com.luis.firstcodes.xml;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.luis.firstcodes.R;

import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class XmlParserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml_parser);
        findViewById(R.id.btn_xml_parse).setOnClickListener(v -> {
            sendRequestWithOkHttp();
        });
    }

    private void sendRequestWithOkHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://192.168.0.108:8080/download/xml")
                            .build();
                    Response response = client.newCall(request).execute();
                    String xmlData = response.body().string();
                    List<Person> persons = parseXmlWithPull(xmlData);
                    persons = sax2xml(xmlData);
                    Toast.makeText(XmlParserActivity.this, persons.toString(), Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    // 使用SAX解析
    public List<Person> sax2xml(String in) {
        try {
            SAXParserFactory spf = SAXParserFactory.newInstance();
            //初始化Sax解析器
            SAXParser sp = spf.newSAXParser();
            //新建解析处理器
            SaxParserHandler handler = new SaxParserHandler();
            //将解析交给处理器
            sp.parse(in, handler);
            //返回List
            return handler.getPersons();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 使用Pull解析
    private List<Person> parseXmlWithPull(String xmlData) {
        List<Person> persons = null;
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlData));
            // 得到第一个事件类型
            int eventType = xmlPullParser.getEventType();
            // 如果事件类型不是文档结束的话则不断处理事件
            Person person = null;
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    // 如果是文档开始事件
                    case XmlPullParser.START_DOCUMENT: {
                        persons = new ArrayList<>();
                        break;
                    }
                    // 如果遇到标签开始
                    case XmlPullParser.START_TAG: {
                        // 获得解析器当前元素的名称
                        String nodeName = xmlPullParser.getName();
                        switch (nodeName) {
                            case "person": {
                                person = new Person();
                                person.setId(xmlPullParser.getAttributeValue(null, "id"));
                                break;
                            }
                            case "age": {
                                if (person != null) {
                                    person.setAge(xmlPullParser.nextText());
                                }
                                break;
                            }
                            case "name": {
                                if (person != null) {
                                    person.setName(xmlPullParser.nextText());
                                }
                                break;
                            }
                        }
                        break;
                    }
                    // 如果遇到标签结束
                    case XmlPullParser.END_TAG: {
                        String nodeName = xmlPullParser.getName();
                        if ("person".equals(nodeName) && person != null && persons != null) {
                            persons.add(person);
                            person = null;
                        }
                        break;
                    }
                }
                //进入下一个事件处理
                eventType = xmlPullParser.next();
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return persons;
    }
}
