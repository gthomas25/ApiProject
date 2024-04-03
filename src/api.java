import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


// video to load jar
//https://www.youtube.com/watch?v=QAJ09o3Xl_0

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;

public class api implements ActionListener {

    org.json.simple.JSONArray jsonArray;
    private JFrame mainFrame;

    public int counter = 0;
    private JTextArea GameofThrones, Name;
    private int WIDTH = 800;
    private int HEIGHT = 700;

    public void pull() throws ParseException {
        String output = "abc";
        String totlaJson = "";
        try {

            URL url = new URL("https://thronesapi.com/api/v2/Characters");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {

                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));


            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                totlaJson += output;
            }

            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONParser parser = new JSONParser();
        //System.out.println(str);
        jsonArray = (org.json.simple.JSONArray) parser.parse(totlaJson);
        System.out.println(jsonArray);

        try {
            for (int i = 0; i < jsonArray.size(); i++) {
                System.out.println(jsonArray.get(i));
                JSONObject secretTunnelGuy = (JSONObject) jsonArray.get(i);
                System.out.println(secretTunnelGuy.get("firstName"));
                System.out.println(secretTunnelGuy.get("title"));

            }

            //put this in a for loop
            //  for ()
//            System.out.println(jsonArray.get(1));
//            JSONObject guy2 = (JSONObject) jsonArray.get(1);secretTunnelGuyAllies
//            System.out.println(guy2.get("name"));
//            org.json.simple.JSONArray guy2Alliess = (JSONArray) secretTunnelGuy.get("allies");
//            System.out.println(guy2Alliess.get(0));


            //   String name = (String)jsonArray.get("name")

            //       org.json.simple.JSONArray msg = (org.json.simple.JSONArray) jsonArray.get("abilities");
            //int n =   msg.size(); //(msg).length();
            //for (int i = 0; i < n; ++i) {
            // String test =(String) msg.get(i);
            //  System.out.println(test);
            // System.out.println(person.getInt("key"));
            // }
            //String name= (String)jsonObject.get("height");
            // System.out.println(name);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public api() throws ParseException {
        prepareGUI();
        pull();
    }

    public static void main(String[] args) throws ParseException {
        api layout = new api();
        layout.showEventDemo();
    }

    private void prepareGUI() {
        mainFrame = new JFrame("Layout");
        mainFrame.setSize(WIDTH, HEIGHT);
        mainFrame.setLayout(new GridLayout(4, 1));

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        mainFrame.setVisible(true);
    }

    private void showEventDemo() {

        JButton nextButton = new JButton("Next");
        JButton previousButton = new JButton("Previous");


        nextButton.setActionCommand("Next");
        previousButton.setActionCommand("Previous");

        nextButton.addActionListener(new ButtonClickListener());
        previousButton.addActionListener(new ButtonClickListener());


        GameofThrones = new JTextArea("Game of Thrones");
        Name = new JTextArea("Names");

        mainFrame.add(GameofThrones);
        mainFrame.add(Name);
        mainFrame.add(nextButton);
        mainFrame.add(previousButton);


        mainFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    private class ButtonClickListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.equals("Next")) {
                counter++;
                if (counter > jsonArray.size() - 1) {
                    counter = 0;
                }
System.out.println("next clicked");
                GameofThrones.setText("");
                Name.setText("");
                System.out.println(jsonArray.get(counter));
                JSONObject secretTunnelGuy = (JSONObject) jsonArray.get(counter);
                System.out.println((String) secretTunnelGuy.get("firstName"));
                GameofThrones.append((String) secretTunnelGuy.get("title"));
                Name.append((String) secretTunnelGuy.get("firstName"));



                // org.json.simple.JSONArray secretTunnelGuyAllies = (JSONArray) secretTunnelGuy.get("allies");
              //  GameofThrones.append((String) secretTunnelGuyAllies.get(0));

                //pokemon.setText("next");
                //allies.setText("next");

            } else if (command.equals("Previous")) {
                counter--;
                if (counter < 0) {
                    counter = 19;
                }
                System.out.println(counter);


                GameofThrones.setText("");
                Name.setText("");
                System.out.println(jsonArray.get(counter));
                JSONObject secretTunnelGuy = (JSONObject) jsonArray.get(counter);
              //  GameofThrones.append((String) secretTunnelGuy.get("name"));
                org.json.simple.JSONArray secretTunnelGuyAllies = (JSONArray) secretTunnelGuy.get("allies");
            //    Name.append((String) secretTunnelGuyAllies.get(0));
                System.out.println((String) secretTunnelGuy.get("firstName"));
                GameofThrones.append((String) secretTunnelGuy.get("title"));
                Name.append((String) secretTunnelGuy.get("firstName"));
                //pokemon.setText("previous");
                //allies.setText("previous");
            } else if (command.equals("Cancel")) {
                //   statusLabel.setText("Cancel Button clicked.");
            } else if (command.equals("startbutton")) {
                // statusLabel.setText("Start Button clicked.");

            } else {
                //  statusLabel.setText("No Button CLicked");
            }
            //

        }
    }
}

