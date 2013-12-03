/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package livestreamerJGUI;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import livestreamerJGUI.GamesInfo.Top;
import livestreamerJGUI.StreamsInfo.Stream;

/**
 *
 * @author Matthias
 */
public class TwitchPicker extends javax.swing.JFrame {

    Map<String, Pair<StreamsInfo, DefaultTableModel>> streamsForGame = new HashMap<>();
    Map<Long, ImageIcon> icons = new HashMap<>();
    private DefaultTableModel gamesTableModel;
    private final DefaultTableModel dummyModel = new DefaultTableModel();
    private String currentGame = "";

    private GamesInfo gamesInfo;

    public GamesInfo getGamesInfo() {
        return gamesInfo;
    }

    public void setGamesInfo(GamesInfo gamesInfo) {
        this.gamesInfo = gamesInfo;
    }

    /**
     * Creates new form TwitchPicker
     */
    public TwitchPicker() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        gameTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        streamTable = new JTable() {    
            public String getToolTipText(MouseEvent e) {
                java.awt.Point p = e.getPoint();
                int rowIndex = rowAtPoint(p);
                int colIndex = columnAtPoint(p);
                return getValueAt(rowIndex, colIndex).toString();
            }
        };
        bSelectGame = new javax.swing.JButton();
        bCancelGameSelect = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(700, 400));
        setResizable(false);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        gameTable.setAutoCreateRowSorter(true);
        gameTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        gameTable.setRowHeight(72);
        gameTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                gameTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(gameTable);

        getContentPane().add(jScrollPane1);

        streamTable.setAutoCreateRowSorter(true);
        streamTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(streamTable);

        getContentPane().add(jScrollPane2);

        bSelectGame.setText("Select");
        bSelectGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSelectGameActionPerformed(evt);
            }
        });
        getContentPane().add(bSelectGame);

        bCancelGameSelect.setText("Cancel");
        bCancelGameSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCancelGameSelectActionPerformed(evt);
            }
        });
        getContentPane().add(bCancelGameSelect);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bSelectGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSelectGameActionPerformed
        String channelName = (String) this.streamTable.getValueAt(this.streamTable.getSelectedRow(), 0);
        String channelURL = getURLForStream(this.currentGame, channelName);
        this.dispose();
        LivestreamerJGUI.getInstance().setEnabled(true);
        LivestreamerJGUI.getInstance().toFront();
        LivestreamerJGUI.getInstance().setBrowseTwitchButtonEnabled(true);
        LivestreamerJGUI.getInstance().setURLFieldText(channelURL);
        List<String> qualities = this.availableQualities(channelName);
        if(!qualities.isEmpty()) {
            JTextArea ta = LivestreamerJGUI.getInstance().getTaOutput();
            ta.append("Available qualities for " + channelName + " (" + channelURL + "):\n");
            int lastElement = qualities.size()-1;
            for(int ctr = 0; ctr <= lastElement; ctr++) {
                String s = qualities.get(ctr);
                ta.append(s + (ctr != lastElement ? ", " : ".\n\n"));
            }
            
        }
    }//GEN-LAST:event_bSelectGameActionPerformed

    private String getURLForStream(String game, String channel) {
        for (Stream s : this.streamsForGame.get(game).getE1().getStreams()) {
            if (s.getChannel().getName().equals(channel)) {
                return s.getChannel().getUrl();
            }
        }
        return null;
    }

    private void gameTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gameTableMouseClicked
        if (this.gameTable.getSelectedRow() == -1) {
            return;
        }
        String gameName = this.gameTable.getValueAt(this.gameTable.getSelectedRow(), 1).toString();
        this.currentGame = gameName;
        if (this.streamsForGame.containsKey(gameName)) {
            this.streamTable.setModel(this.streamsForGame.get(gameName).getE2());
        } else {
            final Class[] classes = new Class[]{String.class, String.class, Integer.class};
            final String[] columnNames = new String[]{"Channel", "Status", "Viewers"};
            final String[] columnIdentifiers = new String[]{"Channel", "Status", "Viewers"};
            DefaultTableModel dtm = createTableModel(classes, columnNames, columnIdentifiers);
            dtm.setColumnCount(3);
            String json;
            String url = "";
            try {
                url = "https://api.twitch.tv/kraken/streams?game=" + URLEncoder.encode(gameName, "UTF-8");
            } catch (UnsupportedEncodingException ex) {
            }
            json = getJsonStringFromTwitchApi(url);

            StreamsInfo streamsInfo = StreamsInfo.fromJson(json);
            for (Stream stream : streamsInfo.getStreams()) {
                Object[] rowData = new Object[3];
                rowData[0] = stream.getChannel().getName();
                rowData[1] = stream.getChannel().getStatus();
                rowData[2] = stream.getViewers();
                dtm.addRow(rowData);
            }
            Pair<StreamsInfo, DefaultTableModel> p = new Pair<>(streamsInfo, dtm);
            this.streamsForGame.put(gameName, p);
            this.streamTable.setModel(this.streamsForGame.get(gameName).getE2());
        }
        System.out.println(gameName);
    }//GEN-LAST:event_gameTableMouseClicked

    private void bCancelGameSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCancelGameSelectActionPerformed
        this.dispose();
        LivestreamerJGUI.getInstance().setEnabled(true);
        LivestreamerJGUI.getInstance().toFront();
        LivestreamerJGUI.getInstance().setBrowseTwitchButtonEnabled(true);
    }//GEN-LAST:event_bCancelGameSelectActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(
                            UIManager.getSystemLookAndFeelClassName());
                } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                }
                final TwitchPicker tp = new TwitchPicker();
                tp.setVisible(true);
                tp.pack();
                tp.setLocationRelativeTo(LivestreamerJGUI.getInstance());
                LivestreamerJGUI.getInstance().setEnabled(false);
                final Class[] classes = new Class[]{ImageIcon.class, String.class, Integer.class};
                final String[] columnNames = new String[]{"", "Game", "Viewers"};
                final String[] columnIdentifiers = new String[]{"Icons", "Game", "Viewers"};
                tp.gamesTableModel = createTableModel(classes, columnNames, columnIdentifiers);
                tp.gamesTableModel.setColumnCount(3);
                tp.gameTable.setModel(tp.gamesTableModel);
                tp.gameTable.createDefaultColumnsFromModel();
                DefaultTableCellRenderer centeredRenderer = new DefaultTableCellRenderer();
                centeredRenderer.setHorizontalAlignment(JLabel.CENTER);
                tp.gameTable.setDefaultRenderer(String.class, centeredRenderer);
                tp.gameTable.setDefaultRenderer(Integer.class, centeredRenderer);
                String url = "https://api.twitch.tv/kraken/games/top?limit=100";
                String json = getJsonStringFromTwitchApi(url);
                tp.gamesInfo = GamesInfo.fromJson(json);

                tp.gameTable.getColumn("").setMinWidth(52);
                tp.gameTable.getColumn("").setMaxWidth(52);

                tp.gameTable.getColumn("Viewers").setMinWidth(60);
                tp.gameTable.getColumn("Viewers").setMaxWidth(60);

                for (Top top : tp.gamesInfo.getTop()) {
                    Object[] data = new Object[3];
                    data[0] = tp.getImageIconForUrl(top.getGame().getId(), top.getGame().getBox().getSmall());
                    data[1] = top.getGame().getName();
                    data[2] = new Integer(top.getViewers());
                    tp.gamesTableModel.addRow(data);
                }
                tp.streamTable.setModel(tp.dummyModel);
                tp.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        tp.bCancelGameSelectActionPerformed(null);
                    }
                });
            }
        });
    }

    public static String getJsonStringFromTwitchApi(String url) {
        InputStream is = null;
        try {
            is = new URL(url).openStream();

            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));

            StringBuilder sb = new StringBuilder();
            int cp;
            while ((cp = rd.read()) != -1) {
                sb.append((char) cp);
            }
            return sb.toString();
        } catch (IOException e) {
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {}
            }
        }
        return null;
    }
    
    public static List<String> parsePlayList(String pl) {
        List<String> result = new ArrayList<>();
        int lastOccurence = 0;
        while (lastOccurence != -1) {
            lastOccurence = pl.indexOf(",NAME=",lastOccurence+1);
            if(lastOccurence != -1) {
                int end = pl.indexOf(",",lastOccurence + 1);
               result.add("mobile_" + pl.substring(lastOccurence + 7, end - 1));
            }
        }
        return result;
    }

    public static DefaultTableModel createTableModel(final Class[] classes, final String[] columnNames, final String[] columnIdentifiers) {
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex < classes.length) {
                    return classes[columnIndex];
                }
                return super.getColumnClass(columnIndex);
            }

            @Override
            public String getColumnName(int column) {
                return columnNames[column];
            }

            @Override
            public boolean isCellEditable(int a, int b) {
                return false;
            }

        };
        model.setColumnIdentifiers(columnIdentifiers);
        return model;
    }

    private ImageIcon getImageIconForUrl(long gameId, String imageUrl) {
        if (!icons.containsKey(gameId)) {
            // check, if the folders have been created yet
            File dir = new File("images/gameicons");
            if(!dir.exists()) {
                dir.mkdirs();
            }
            BufferedImage img = null;

            try {
                //URL localUrl = getClass().getResource("images/gameicons/" + gameId + ".jpg");
                File file = new File("images/gameicons/" + gameId + ".jpg");
                if (file.exists()) {
                    img = ImageIO.read(file);
                }
                // Icon for game not present, try to download it
                if (img == null) {
                    System.out.println("Trying to Retrieve Image for GameID " + gameId + ", url=" + imageUrl);
                    URL url = new URL(imageUrl);
                    img = ImageIO.read(url);
                    if (img != null) {
                        ImageIO.write(img, "jpg", new File("images/gameicons/" + gameId + ".jpg"));
                    }
                }
                if (img == null) {
                    // not found locally, couldn't download it either. Taking the placeholder instead.
                    img = ImageIO.read(getClass().getResource("images/gameicons/NoIcon.jpg"));
                }

                this.icons.put(gameId, new ImageIcon(img));

            } catch (IOException e) {
            }
        }
        return this.icons.get(gameId);
    }
    
    private List<String> availableQualities(String channelName) {
        List<String> result = new ArrayList<>();
        JsonParser o = new JsonParser();
        int random = (int)Math.floor(Math.random() * 999999);
        String json = getJsonStringFromTwitchApi("http://usher.justin.tv/find/" + channelName + ".json?p=" + random + "&type=any");
        if (json == null) {
            return result;
        }
        try {
            JsonArray array = o.parse(json).getAsJsonArray();
            for(JsonElement e : array) {
            if(e.isJsonObject()) {
                JsonObject current = e.getAsJsonObject();
                result.add(current.get("display").getAsString());
            }
        }
        } catch(JsonSyntaxException e) {
            
        }
        Pair<String,String> tokenAndSig = getTokenAndSig(channelName);
        String mobile_url = "http://usher.twitch.tv/api/channel/hls/" + channelName + ".m3u8?allow_source=true&token=" + tokenAndSig.getE1() + "&sig=" + tokenAndSig.getE2();
        mobile_url = mobile_url.replace("\\\"", "\"");
        String playList = getJsonStringFromTwitchApi(mobile_url);
        for(String s : parsePlayList(playList)) {
            result.add(s);
        }
        return result;
    }
    
    private Pair<String,String> getTokenAndSig(String channelName) {
        String url = "https://api.twitch.tv/api/channels/" + channelName + "/access_token";
        String json = getJsonStringFromTwitchApi(url);
        JsonParser o = new JsonParser();
        JsonObject tokenObj = o.parse(json).getAsJsonObject();
        return new Pair(tokenObj.get("token").getAsString(),tokenObj.get("sig").getAsString());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCancelGameSelect;
    private javax.swing.JButton bSelectGame;
    private javax.swing.JTable gameTable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable streamTable;
    // End of variables declaration//GEN-END:variables
}
