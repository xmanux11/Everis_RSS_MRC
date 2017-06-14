package com.example.manumadrid.everis_rss;

import android.os.AsyncTask;
import android.util.Log;
import android.util.Xml;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by ManuMadrid on 14/06/2017.
 */

/**
 * Clase encargada de la coenxion para la descarga del xml perteneciente al rss
 */

public class GetXMLTask extends AsyncTask<Void, Void, InputStream> {

    /**
     * listener desde el cual obtendremos el resultado de la tarea
     */
    private GetXMLListener listener;

    public GetXMLTask(GetXMLListener listener) {
        this.listener = listener;
    }

    @Override
    protected InputStream doInBackground(Void... params) {
        InputStream is = null;
        try {
            //Conexion a el RSS
            URL url = new URL("http://ep00.epimg.net/rss/tecnologia/portada.xml");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(10 * 1000);
            connection.setConnectTimeout(10 * 1000);
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();
            is = connection.getInputStream();
            News newsData = parseFeed(is);
            is.close();
            listener.onReady(newsData);
        } catch (MalformedURLException e) {
            listener.onError("error: " + e.getMessage());
        } catch (IOException e) {
            listener.onError("error: " + e.getMessage());
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        return is;
    }

    public interface GetXMLListener {
        void onReady(News news);

        void onError(String error);
    }

    /**
     * Metodo para el procesamiento de datos del xml
     *
     * @param inputStream
     * @return Devuelve una lista de noticias sin procesar
     * @throws XmlPullParserException
     * @throws IOException
     */
    public News parseFeed(InputStream inputStream) throws XmlPullParserException, IOException {
        String title = null;
        String link = null;
        String description = null;
        String imageUrl = null;
        boolean isItem = false;
        ArrayList<New> items = new ArrayList<>();

        XmlPullParser xmlPullParser = Xml.newPullParser();
        xmlPullParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        xmlPullParser.setInput(inputStream, null);

        xmlPullParser.nextTag();
        while (xmlPullParser.next() != XmlPullParser.END_DOCUMENT) {
            int eventType = xmlPullParser.getEventType();
            String name = xmlPullParser.getName();
            if (name == null)
                continue;

            if (eventType == XmlPullParser.END_TAG) {
                if (name.equalsIgnoreCase("item")) {
                    isItem = false;
                }
                continue;
            }

            if (eventType == XmlPullParser.START_TAG) {
                if (name.equalsIgnoreCase("item")) {
                    isItem = true;
                    continue;
                }
            }
            String result = "";
            if (xmlPullParser.next() == XmlPullParser.TEXT) {
                result = xmlPullParser.getText();
                xmlPullParser.nextTag();
            }

            if (name.equalsIgnoreCase("title")) {
                title = result;
            } else if (name.equalsIgnoreCase("link")) {
                link = result;
            } else if (name.equalsIgnoreCase("description")) {
                description = result;
            } else if (name.equalsIgnoreCase("enclosure")) {
                try {
                    imageUrl = xmlPullParser.getAttributeValue(0);
                } catch (Exception e) {
                    Log.d("GetXMLTask","error al obtener la imagen");
                }

            }

            if (title != null && link != null && description != null&&imageUrl!=null) {
                if (isItem) {
                    New item = new New(title, description, imageUrl, link);
                    items.add(item);
                }
                title = null;
                link = null;
                description = null;
                isItem = false;
            }
        }
        ArrayList<String> titles = new ArrayList<>();
        ArrayList<String> descriptions = new ArrayList<>();
        ArrayList<String> images = new ArrayList<>();
        ArrayList<String> urls = new ArrayList<>();
        for (New data : items) {
            titles.add(data.getTitle());
            descriptions.add(data.getDescription());
            images.add(data.getImageUrl());
            urls.add(data.getUrlNew());
        }
        News newsFinal = new News(titles, descriptions, images, urls);
        return newsFinal;
    }
}
