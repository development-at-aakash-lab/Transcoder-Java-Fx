package test93;

import com.sun.javafx.css.Style;

import javafx.stage.StageStyle;


import java.io.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Dimension2D;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import javafx.stage.*;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class test100 extends Application {

    static GetDuration time_diff_obj;
    static Stage secondaryStage;
    static ProgressBar pb_convert, pb_split;
    static Label label, split_label;
    static Group root;
    static GridPane grid, split_grid;
    static String status, default_op_path;
    static ProcessBuilder process_builder_var1, process_builder_var2;
    static Date date;
    File file;
    // GetDuration obj3;
    static String time_diff, preset, s_preset;
    static String st1hh, st1mm, st1ss, et1hh, et1mm, et1ss, split_file_name1, split_file_name2, split_file_name3;
    static String st2hh, st2mm, st2ss, et2hh, et2mm, et2ss;
    static String st3hh, st3mm, st3ss, et3hh, et3mm, et3ss;
    static ComboBox aspect_ratio_cb, resolution_cb, frame_rate_cb, videobit_rate_cb, audiobit_rate_cb, audiosample_rate_cb, audio_channel_cb;
    static ComboBox split_aspect_ratio_cb, split_resolution_cb, split_frame_rate_cb, split_vediobit_rate_cb, split_audiobit_rate_cb, split_audiosample_rate_cb, split_audio_channel_cb;
    static Button browse_btn, convert_btn, btn3, btn4, rem_btn, dest_folder_button, cancel_button, clear_btn, split_dest_button;
    static Button split_main_btn;
    //btn3   split
    static String path, inp, inp2, op, output_path, split_output_path;
    static RadioButton rb1, rb2, rb3, rb4, rb5, rb6, rb7, rb8, rb9, rb10, rb11;
    static RadioButton split_rb1, split_rb2, split_rb3, split_rb4, split_rb5, split_rb6, split_rb7, split_rb8, split_rb9, split_rb10, split_rb11;
    static String format, default_format, split_attribs, attribs, cmnd1, cmnd2, cmnd3, cmnd4, split_format;
    static String[] duratn;
    // static Dimension2D ff;
    static int phh, pmm, pss, hh, mm, ss, i, slect, format_flag, split_format_flag, conv_flag, freeze_var;
    static float psss;
    Thread t, t2, t3, cancel_thread;
    static Text actiontarget, text, inp_path, split_actiontarget, spname_file1, spname_file2, spname_file3, split_audio_format, split_video_format, scenetitle;
    static TextField sp_time1_hh, sp_time1_mm, sp_time1_ss, sp_time2_hh, sp_time2_mm, sp_time2_ss, dest_path, spname_field1, spname_field2, spname_field3, split_dest_path;
    static TextField sp_time3_hh, sp_time3_mm, sp_time3_ss, sp_time4_hh, sp_time4_mm, sp_time4_ss;
    static TextField sp_time5_hh, sp_time5_mm, sp_time5_ss, sp_time6_hh, sp_time6_mm, sp_time6_ss;
    static String pval[] = {"-aspect", "-b", "-r", "-s", "-ab", "-ac", "-ar"};
    static String presets[] = {"", "", "", "", "", "", ""};
    static String split_pval[] = {"-aspect", "-b", "-r", "-s", "-ab", "-ac", "-ar"};
    static String split_presets[] = {"", "", "", "", "", "", ""};
    static int freeze_convert[] = {0, 0, 0, 0, 0, 0, 0, 0};
    static int convert_counter;
    static String osname, play_vid, play_path, play_inp;
    static ImageView iv1;
    ListView<String> list = new ListView<String>();
    ObservableList<String> data = FXCollections.observableArrayList();

    public static void main(String[] args) throws Exception {

        launch(args);

    }

    public void start(final Stage primaryStage) {


preset=" ";
s_preset=" ";
        conv_flag = 0;
        format = "";
        format_flag = 0;
        osname = System.getProperty("os.name");
        System.out.println(osname);
        if (osname.compareToIgnoreCase("linux") == 0) {
            cmnd1 = "gnome-terminal";//win
            cmnd2 = "-x";//win
            cmnd3 = "bash";
            cmnd4 = "-c";

        } else {
            cmnd1 = "cmd";//win
            cmnd2 = "/C";//win
        }
        System.out.println("cmnd1=  " + cmnd1);
        System.out.println(cmnd2);
        output_path = "";



        //  Image image = new Image("background.jpg");

        System.out.println("Thread count at Start:" + Thread.activeCount());

        list.setMaxHeight(100);
        list.setItems(data);





        list.setOnDragEntered(new EventHandler<DragEvent>() {

            public void handle(DragEvent arg0) {
                /*
                 * the drag-and-drop gesture entered the list
                 */
                Object obj1 = arg0.getDragboard().getContent(DataFormat.FILES);

                ArrayList alist1 = (ArrayList) obj1;
                //  content.putString(file.getPath());
                //     db.setContent(content);
                String temp = alist1.toString();
                temp = temp.substring(1, temp.length() - 1);
                String files[];
                files = temp.split(",");
                for (int i = 0; i < files.length; i++) {

                    if (data.size() == 0) {
                        data.add(files[i].trim());
                    } else {
                        data.add(data.size(), files[i].trim());
                    }
                }
                //data.add(temp.substring(1, temp.length() - 1));




                // event.consume();
            }
        });




        audiosample_rate_cb = new ComboBox();

        audio_channel_cb = new ComboBox();
        audiobit_rate_cb = new ComboBox();
        videobit_rate_cb = new ComboBox();
        frame_rate_cb = new ComboBox();
        resolution_cb = new ComboBox();
        aspect_ratio_cb = new ComboBox();
        //   cb1 = new ComboBox();
        // cb2 = new ComboBox();
        pb_convert = new ProgressBar();
        pb_convert.setProgress(0f);
        pb_convert.setScaleX(4);
        pb_convert.setScaleY(2);

        //pb_convert.getMaxWidth();
        actiontarget = new Text();
        dest_path = new TextField();
        //  dest_path.setEditable(false);
        //    actiontarget.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 20));
        actiontarget.setFill(Color.RED);
        label = new Label();
        inp_path = new Text();

        root = new Group();
        final ToggleGroup group2 = new ToggleGroup();
        primaryStage.setTitle("Multimedia Transcoder");
        final Label labelFile = new Label();


        browse_btn = new Button();//browse
        cancel_button = new Button("Cancel");
        dest_folder_button = new Button("Destination");
        convert_btn = new Button();
        clear_btn = new Button(); //clear

        btn3 = new Button("SPLIT");//split
        //     btn3.setFont(Font.font("Vivaldi", 17));
        btn4 = new Button("Play");
        rem_btn = new Button("Remove");
        rb1 = new RadioButton("Default");
        rb1.setToggleGroup(group2);
        //     rb1.setFont(font);
        rb1.setUserData("default");

        rb2 = new RadioButton("webM");
        rb2.setToggleGroup(group2);
        rb2.setUserData("webm");
        //   rb2.setFont(font);
        rb3 = new RadioButton("ogv");
        rb3.setToggleGroup(group2);
        rb3.setUserData("ogv");
        //     rb3.setFont(font);
        rb4 = new RadioButton("mp3");
        rb4.setToggleGroup(group2);
        rb4.setUserData("mp3");
        //    rb4.setFont(font);
        rb5 = new RadioButton("mp4");
        rb5.setToggleGroup(group2);
        rb5.setUserData("mp4");
        //     rb5.setFont(font);
        rb6 = new RadioButton("avi");
        rb6.setToggleGroup(group2);
        rb6.setUserData("avi");
        //      rb6.setFont(font);

        rb7 = new RadioButton("mpeg");
        rb7.setToggleGroup(group2);
        rb7.setUserData("mpeg");
        //      rb7.setFont(font);

        rb8 = new RadioButton("wmv");
        rb8.setToggleGroup(group2);
        rb8.setUserData("wmv");
        //      rb8.setFont(font);

        rb9 = new RadioButton("flv");
        rb9.setToggleGroup(group2);
        rb9.setUserData("flv");
        //     rb9.setFont(font);

        rb10 = new RadioButton("wav");
        rb10.setToggleGroup(group2);
        rb10.setUserData("wav");
        ///      rb10.setFont(font);

        rb11 = new RadioButton("mp4(H264)");
        rb11.setToggleGroup(group2);
        rb11.setUserData("mp4h");


        GridPane grid = addGridPane();

        //  grid.add(lab2,4,10);

        root.getChildren().add(grid);



        browse_btn.setText("Browse...");
        convert_btn.setText("Convert");
        clear_btn.setText("Clear All");


        ///FILE BROWSE 
        browse_btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                FileChooser fileChooser = new FileChooser();
                //Set extension filter
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Media Files(all types)", "*.mp4;*.avi;*wmv;*.flv;*.webm;*.ogg;*.mp3;*.mkv;*.ogv");
                //       FileChooser.ExtensionFilter extFilter1 = new FileChooser.ExtensionFilter("AVI files (*.avi)", "*.avi");
                fileChooser.getExtensionFilters().addAll(extFilter);//, extFilter1);

                //     fileChooser.getExtensionFilters().add(2, extFilter1);
                //Show open file dialog
                List file_list = fileChooser.showOpenMultipleDialog(null);
                //labelFile.setText(file.getPath());
                String file_list2 = (String) file_list.toString();
                file_list2 = file_list2.substring(1, file_list2.length() - 1);
                String files[];
                files = file_list2.split(",");
                for (int i = 0; i < files.length; i++) {

                    if (data.size() == 0) {
                        data.add(files[i].trim());
                    } else {
                        data.add(data.size(), files[i].trim());
                    }
                }


            }
        });  ///browse_btn set on action


///convert button
        // TRANSCODE   





        cancel_button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {






                String[] command = {cmnd1, cmnd2, "  taskkill /f /IM  ffmpeg.exe && del \\f \"" + op + "\""};
                process_builder_var1 = new ProcessBuilder(command);
                process_builder_var1.redirectErrorStream(true); // merge stdout, stderr of process
                //   process_builder_var1.directory(new File(path));






                try {

                    Process p = process_builder_var1.start();
                    //  event.consume();


                    InputStreamReader isr = new InputStreamReader(p.getInputStream());
                    final BufferedReader br = new BufferedReader(isr);
                    //RUN 1
                    cancel_thread = new Thread(new Runnable() {

                        public void run() {

                            String lineRead;

                            try {

                                while ((lineRead = br.readLine()) != null) {

                                    System.out.println("lineRead" + lineRead);
                                }





                            } catch (IOException e) {

                                e.printStackTrace();

                            } finally {

                                if (br != null) {

                                    try {

                                        br.close();

                                    } catch (IOException e) {

                                        e.printStackTrace();

                                    }

                                }

                            }

                        }
                    });
                    cancel_thread.setPriority(8);
                    cancel_thread.start();

                } catch (IOException e) {
                    e.printStackTrace(); // or log it, or otherwise handle it
                }



                convert_counter = 99999;
                System.out.println("CANCEL!!!");

            }
        });  ///browse_btn set on action




        dest_folder_button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {


                DirectoryChooser directoryChooser = new DirectoryChooser();
                directoryChooser.setTitle("Select Destination Folder");
                File file = directoryChooser.showDialog(null);
                if (file != null) {
                    output_path = file.getPath();


                    //dest_path=output_path;
                    ////////////////////////////////////////FIX FOR WINDOWS*************************************************
                    output_path += "\\";

                    dest_path.setText(output_path);

                }
                System.out.println("Output path: " + output_path);
            }
        });   /////convert_btn   set on action



        convert_btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {



                output_path = dest_path.getText();

                Thread t29 = new Thread(new Runnable() {

                    public void run() throws NullPointerException {


                        try {

                            System.out.println("Thread count at convert:" + Thread.activeCount());
                            System.out.println("       " + data.size());
                            for (convert_counter = 0; convert_counter < (data.size()); convert_counter++) {

                                transcode(convert_counter);
                                while (t2.isAlive() || t.isAlive()) {
                                }

                            }
                            System.out.println("Thread count at end of convert:" + Thread.activeCount());
                        } catch (Exception e) {

                            e.printStackTrace();

                        }

                    }
                });
                t29.setPriority(2);
                t29.start();
            }////eof handle()
        });   /////convert_btn   set on action













        ////split...
        btn3.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                s_preset = "";
                Text st1txt = new Text("Start Time:");
                Text et1txt = new Text("End Time:");
                Text st2txt = new Text("Start Time:");
                Text et2txt = new Text("End Time:");
                Text st3txt = new Text("Start Time:");
                Text et3txt = new Text("End Time:");
                Text spname_file1 = new Text("Enter file name ");
                Text spname_file2 = new Text("Enter file name ");
                Text spname_file3 = new Text("Enter file name ");


                Text split_title = new Text("Split Vid");
                split_format_flag = 0;

                split_dest_button = new Button("Destination");
                split_dest_path = new TextField();
                split_main_btn = new Button("Split");
                sp_time1_hh = new TextField();
                sp_time1_mm = new TextField();
                sp_time1_ss = new TextField();
                sp_time2_hh = new TextField();
                sp_time2_mm = new TextField();
                sp_time2_ss = new TextField();
                sp_time3_hh = new TextField();
                sp_time3_mm = new TextField();
                sp_time3_ss = new TextField();
                sp_time4_hh = new TextField();
                sp_time4_mm = new TextField();
                sp_time4_ss = new TextField();
                sp_time5_hh = new TextField();
                sp_time5_mm = new TextField();
                sp_time5_ss = new TextField();
                sp_time6_hh = new TextField();
                sp_time6_mm = new TextField();
                sp_time6_ss = new TextField();
                spname_field1 = new TextField();
                spname_field2 = new TextField();
                spname_field3 = new TextField();
                sp_time1_hh.setMaxWidth(30);
                sp_time1_mm.setMaxWidth(30);
                sp_time1_ss.setMaxWidth(30);
                sp_time2_hh.setMaxWidth(30);
                sp_time2_mm.setMaxWidth(30);
                sp_time2_ss.setMaxWidth(30);
                sp_time3_hh.setMaxWidth(30);
                sp_time3_mm.setMaxWidth(30);
                sp_time3_ss.setMaxWidth(30);
                sp_time4_hh.setMaxWidth(30);
                sp_time4_mm.setMaxWidth(30);
                sp_time4_ss.setMaxWidth(30);
                sp_time5_hh.setMaxWidth(30);
                sp_time5_mm.setMaxWidth(30);
                sp_time5_ss.setMaxWidth(30);
                sp_time6_hh.setMaxWidth(30);
                sp_time6_mm.setMaxWidth(30);
                sp_time6_ss.setMaxWidth(30);
                secondaryStage = new Stage();
                secondaryStage.setTitle("Split Video");
                Group split_grp = new Group();
                split_grid = new GridPane();
                ///        split_title.setFont(Font.font("Tahoma", FontWeight.BOLD, 16));
                split_grid.add(split_title, 0, 0);





                split_grid.add(st1txt, 10, 3);//st split time
                split_grid.add(sp_time1_hh, 11, 3);
                split_grid.add(sp_time1_mm, 12, 3);
                split_grid.add(sp_time1_ss, 13, 3);
                split_grid.add(et1txt, 10, 4);//st split time
                split_grid.add(sp_time2_hh, 11, 4);
                split_grid.add(sp_time2_mm, 12, 4);
                split_grid.add(sp_time2_ss, 13, 4);


                split_grid.add(st2txt, 10, 5);//st split time
                split_grid.add(sp_time3_hh, 11, 5);
                split_grid.add(sp_time3_mm, 12, 5);
                split_grid.add(sp_time3_ss, 13, 5);
                split_grid.add(et2txt, 10, 6);
                //st split time
                split_grid.add(sp_time4_hh, 11, 6);
                split_grid.add(sp_time4_mm, 12, 6);
                split_grid.add(sp_time4_ss, 13, 6);



                split_grid.add(st3txt, 10, 7);//st split time
                split_grid.add(sp_time5_hh, 11, 7);
                split_grid.add(sp_time5_mm, 12, 7);
                split_grid.add(sp_time5_ss, 13, 7);
                split_grid.add(et3txt, 10, 8);//st split time
                split_grid.add(sp_time6_hh, 11, 8);
                split_grid.add(sp_time6_mm, 12, 8);
                split_grid.add(sp_time6_ss, 13, 8);



                split_grid.setHgap(6);
                split_grid.setVgap(6);

                Text hn = new Text("dc");
                split_grid.add(spname_file1, 8, 3);
                split_grid.add(spname_file2, 8, 5);
                split_grid.add(spname_file3, 8, 7);


                split_grid.add(spname_field1, 9, 3);
                split_grid.add(spname_field2, 9, 5);
                split_grid.add(spname_field3, 9, 7);

                split_audiosample_rate_cb = new ComboBox();
                split_audio_channel_cb = new ComboBox();
                split_audiobit_rate_cb = new ComboBox();
                split_vediobit_rate_cb = new ComboBox();
                split_frame_rate_cb = new ComboBox();
                split_resolution_cb = new ComboBox();
                split_aspect_ratio_cb = new ComboBox();
                //   split_cb1 = new ComboBox();
                //   split_cb2 = new ComboBox();
                pb_split = new ProgressBar();
                pb_split.setProgress(0f);
                pb_split.setScaleX(4);
                pb_split.setScaleY(2);
                split_actiontarget = new Text();
                //      split_actiontarget.setFont(Font.font("Tahoma", 20));
                split_actiontarget.setFill(Color.RED);
                inp_path = new Text();
                final ToggleGroup split_group2 = new ToggleGroup();
                final Label split_labelFile = new Label();
                split_rb1 = new RadioButton("Default");
                split_rb1.setToggleGroup(split_group2);
                split_rb1.setUserData("default");
                split_rb2 = new RadioButton("WebM");
                split_rb2.setToggleGroup(split_group2);
                split_rb2.setUserData("webm");
                split_rb3 = new RadioButton("Ogv");
                split_rb3.setToggleGroup(split_group2);
                split_rb3.setUserData("ogv");
                split_rb4 = new RadioButton("Mp3");
                split_rb4.setToggleGroup(split_group2);
                split_rb4.setUserData("mp3");
                split_rb5 = new RadioButton("Mp4");
                split_rb5.setToggleGroup(split_group2);
                split_rb5.setUserData("mp4");

                split_rb6 = new RadioButton("avi");
                split_rb6.setToggleGroup(split_group2);
                split_rb6.setUserData("avi");

                split_rb7 = new RadioButton("mpeg");
                split_rb7.setToggleGroup(split_group2);
                split_rb7.setUserData("mpeg");

                split_rb8 = new RadioButton("wmv");
                split_rb8.setToggleGroup(split_group2);
                split_rb8.setUserData("wmv");
                
                 split_rb9 = new RadioButton("flv");
                split_rb9.setToggleGroup(split_group2);
                split_rb9.setUserData("flv");
                
                 split_rb10 = new RadioButton("wav");
                split_rb10.setToggleGroup(split_group2);
                split_rb10.setUserData("wav");



                split_rb11 = new RadioButton("mp4(H264)");
                split_rb11.setToggleGroup(split_group2);
                split_rb11.setUserData("mp4h");

                split_rb1.setSelected(true);

                Text split_ar = new Text("Aspect-Ratio:");
                Text split_fr = new Text("Frame-Rate:");
                Text split_vbr = new Text("Video Bit-Rate:");
                Text split_res = new Text("Resolution:");
                Text split_abr = new Text("Audio Bit-Rate:");
                Text split_asr = new Text("Audio Sample-Rate:");
                Text split_ac = new Text("Audio Channels:");
                split_audio_format = new Text("Audio Formats:");
                split_video_format = new Text("Video Formats:");


                split_grp.getStylesheets().add(getClass().getResource("style2.css").toExternalForm());
                //convert_btn.getStyleClass().add("convert_btn"); 
                split_main_btn.getStyleClass().add("convert_btn");
                 split_actiontarget.getStyleClass().add("splitprogtext");
 
                split_title.getStyleClass().add("title");
                split_grid.getStyleClass().add("root");
                split_aspect_ratio_cb.getItems().addAll(
                        "16:9",
                        "4:3", "Default");
                split_aspect_ratio_cb.setPromptText("Default");
                split_aspect_ratio_cb.setEditable(false);

                split_frame_rate_cb.getItems().addAll(
                        "15",
                        "20", "24", "25", "30", "60", "Default");
                split_frame_rate_cb.setPromptText("Default");
                split_frame_rate_cb.setEditable(false);

                //   if(split_aspect_ratio_cb.getPromptText=="Default")

                split_vediobit_rate_cb.getItems().addAll(
                        "200", "350", "500", "700", "850", "1000", "1024", "Default");
                split_vediobit_rate_cb.setPromptText("Default");
                split_vediobit_rate_cb.setEditable(false);


                split_resolution_cb.getItems().addAll(
                        "160x128", "290x240", "320x240", "360x240", "640x480", "720x576", "1024x768", "Default");
                split_resolution_cb.setPromptText("Default");
                split_resolution_cb.setEditable(false);

                split_audiobit_rate_cb.getItems().addAll("64", "128", "150", "160", "200", "Default");
                split_audiobit_rate_cb.setPromptText("Default");
                split_audiobit_rate_cb.setEditable(false);


                split_audiosample_rate_cb.getItems().addAll(
                        "8000", "11025", "16000", "22050", "32000", "44100", "48000", "Default");
                split_audiosample_rate_cb.setPromptText("Default");
                split_audiosample_rate_cb.setEditable(false);



                split_audio_channel_cb.getItems().addAll(
                        "1", "2", "Default");
                split_audio_channel_cb.setPromptText("Default");
                split_audio_channel_cb.setEditable(false);


                split_grid.add(split_video_format, 1, 3);
                split_grid.add(split_rb1, 2, 4);//DEFAULTTTT
                split_grid.add(split_rb5, 2, 5);//mp4
                split_grid.add(split_rb2, 2, 6);//webm
                split_grid.add(split_rb3, 2, 7);//ogv


                split_grid.add(split_rb6, 5, 4);//avi
                split_grid.add(split_rb7, 5, 5);//mpeg
                split_grid.add(split_rb8, 5, 6);//wmv
                split_grid.add(split_rb9, 5, 7);//wmv
                split_grid.add(split_rb10, 7, 5);//wav
                
                split_grid.add(split_rb11, 2, 8);//mp4(h264)
                split_grid.add(split_audio_format, 6, 3);
                split_grid.add(split_rb4, 7, 4);//mp3 
                //grid.setPrefWidth(10);


                split_grid.add(split_aspect_ratio_cb, 2, 13);//aspect ratio
                split_grid.add(split_ar, 1, 13);//aspect ratio

                split_grid.add(split_frame_rate_cb, 2, 14);//frame rate
                split_grid.add(split_fr, 1, 14);//frame rate


                split_grid.add(split_vediobit_rate_cb, 2, 15);//vbit rate
                split_grid.add(split_vbr, 1, 15);//vbit rate

                split_grid.add(split_resolution_cb, 2, 16);//resltn
                split_grid.add(split_res, 1, 16);//resltn


                //    grid.add(resolution_cb, 5, 7);//resltn
                //  grid.add(res, 4, 7);//resltn


                split_grid.add(split_audiobit_rate_cb, 7, 13);//
                split_grid.add(split_abr, 6, 13);//

                split_grid.add(split_audiosample_rate_cb, 7, 14);//
                split_grid.add(split_asr, 6, 14);//

                split_grid.add(split_audio_channel_cb, 7, 15);//
                split_grid.add(split_ac, 6, 15);//





                split_grid.add(split_dest_button, 6, 10);
                split_dest_path.setMaxWidth(180);
                split_dest_path.setMaxHeight(100);
                split_grid.add(split_dest_path, 5, 10);


                split_grid.add(split_main_btn, 6, 20);//split button

                //   split_grid.add(btn4, 5, 26);//play button

                split_grid.add(split_actiontarget, 9, 18);
                split_grid.add(pb_split, 6, 18);//progress

                //   split_grid.add(convert_btn, 3, 26);//convert


                split_grp.getChildren().add(split_grid);
                Scene split_scene = new Scene(split_grp, 1055, 445);
                secondaryStage.setScene(split_scene);



                // show both stages.
                // primaryStage.show();
                secondaryStage.setResizable(false);
                secondaryStage.show();

                primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

                    @Override
                    public void handle(WindowEvent onClosing) {
                        secondaryStage.close();
                    }
                });


                /*
                 * primaryStage.setOnHidden(new EventHandler<WindowEvent>() {
                 *
                 * @Override public void handle(WindowEvent event) {
                 * secondaryStage.hide(); } });
                 */
                /*
                 * primaryStage.setOnShowing(new EventHandler<WindowEvent>() {
                 *
                 *
                 * @Override public void handle(WindowEvent onEvent) {
                 * secondaryStage.show(); } });
                 */


                split_group2.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

                    public void changed(ObservableValue<? extends Toggle> ov,
                            Toggle old_toggle, Toggle new_toggle) {
                        if (split_group2.getSelectedToggle() != null) {

                            String str = split_group2.getSelectedToggle().getUserData().toString();
                            if (str.compareToIgnoreCase("default") == 0) {
                                split_format_flag = 0;


                            } else {

                                split_format_flag = 1;
                                split_format = str;
                                s_preset = "";
                                if (str.compareTo("mp4h") == 0) {

                                    split_format = "mp4";
                                    s_preset = " -vcodec libx264 ";
                                }
                                if (str.compareTo("mp4") == 0) {

                                    split_format = "mp4";
                                    s_preset = " -vcodec libxvid ";
                                }

                            }
                            System.out.println("split flag:" + split_format_flag);


                        }
                    }
                });




                split_dest_button.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {


                        DirectoryChooser directoryChooser = new DirectoryChooser();
                        directoryChooser.setTitle("Select Split Destination Folder");
                        File file = directoryChooser.showDialog(null);
                        if (file != null) {
                            split_output_path = file.getPath();



                            ////////////////////////////////////////FIX FOR WINDOWS*************************************************
                            split_output_path += "\\";

                            split_dest_path.setText(split_output_path);

                        }

                    }
                });





                split_aspect_ratio_cb.valueProperty().addListener(new ChangeListener<String>() {

                    @Override
                    public void changed(ObservableValue ov, String t, String t1) {

                        System.out.print(t1);
                        if (t1.compareToIgnoreCase("default") == 0) {
                            split_presets[0] = "";
                        } else {
                            split_presets[0] = t1;
                        }
                    }
                });

                split_vediobit_rate_cb.valueProperty().addListener(new ChangeListener<String>() {

                    @Override
                    public void changed(ObservableValue ov, String t, String t1) {

                        System.out.print(t1);
                        if (t1.compareToIgnoreCase("default") == 0) {
                            split_presets[1] = "";
                        } else {
                            split_presets[1] = t1;
                        }
                    }
                });


                split_frame_rate_cb.valueProperty().addListener(new ChangeListener<String>() {

                    @Override
                    public void changed(ObservableValue ov, String t, String t1) {

                        System.out.print(t1);
                        if (t1.compareToIgnoreCase("default") == 0) {
                            split_presets[2] = "";
                        } else {
                            split_presets[2] = t1;
                        }
                    }
                });



                split_resolution_cb.valueProperty().addListener(new ChangeListener<String>() {

                    @Override
                    public void changed(ObservableValue ov, String t, String t1) {

                        System.out.print(t1);
                        if (t1.compareToIgnoreCase("default") == 0) {
                            split_presets[3] = "";
                        } else {
                            split_presets[3] = t1;
                        }
                    }
                });



                split_audiobit_rate_cb.valueProperty().addListener(new ChangeListener<String>() {

                    @Override
                    public void changed(ObservableValue ov, String t, String t1) {

                        System.out.print(t1);
                        if (t1.compareToIgnoreCase("default") == 0) {
                            split_presets[4] = "";
                        } else {
                            split_presets[4] = t1;
                        }
                    }
                });


                split_audio_channel_cb.valueProperty().addListener(new ChangeListener<String>() {

                    @Override
                    public void changed(ObservableValue ov, String t, String t1) {

                        System.out.print(t1);
                        if (t1.compareToIgnoreCase("default") == 0) {
                            split_presets[5] = "";
                        } else {
                            split_presets[5] = t1;
                        }
                    }
                });

                split_audiosample_rate_cb.valueProperty().addListener(new ChangeListener<String>() {

                    @Override
                    public void changed(ObservableValue ov, String t, String t1) {

                        System.out.print(t1);
                        if (t1.compareToIgnoreCase("default") == 0) {
                            split_presets[6] = "";
                        } else {
                            split_presets[6] = t1;
                        }
                    }
                });







                split_main_btn.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {




                        st1hh = sp_time1_hh.getText();
                        st1mm = sp_time1_mm.getText();
                        st1ss = sp_time1_ss.getText();
                        et1hh = sp_time2_hh.getText();
                        et1mm = sp_time2_mm.getText();
                        et1ss = sp_time2_ss.getText();

                        System.out.print(st1hh);
                        System.out.print(st1mm);

                        System.out.print(st1ss);
                        System.out.print(et1hh);
                        System.out.print(et1mm);
                        System.out.print(et1ss);
                        st2hh = sp_time3_hh.getText();
                        st2mm = sp_time3_mm.getText();
                        st2ss = sp_time3_ss.getText();
                        et2hh = sp_time4_hh.getText();
                        et2mm = sp_time4_mm.getText();
                        et2ss = sp_time4_ss.getText();


                        st3hh = sp_time5_hh.getText();
                        st3mm = sp_time5_mm.getText();
                        st3ss = sp_time5_ss.getText();
                        et3hh = sp_time6_hh.getText();
                        et3mm = sp_time6_mm.getText();
                        et3ss = sp_time6_ss.getText();


                        split_init();
                        split_file_name1 = "";
                        split_file_name2 = "";
                        split_file_name3 = "";


                        split_file_name1 = spname_field1.getText();
                        split_file_name2 = spname_field2.getText();
                        split_file_name3 = spname_field3.getText();
                        Thread t29 = new Thread(new Runnable() {

                            public void run() throws NullPointerException {

                                try {


                                    int sflag = 0;
                                    for (int cntr = 1; cntr < 4; cntr++) {
                                        sflag = 0;
                                        if (cntr == 1) {
                                            if (st1ss.length() == 0) {
                                            } else {
                                                sflag = 1;
                                                System.out.println("SPLIT 1:");
                                                split_function(st1hh, st1mm, st1ss, et1hh, et1mm, et1ss, split_file_name1);
                                            }
                                        }

                                        if (cntr == 2) {
                                            if (st2ss.length() == 0) {
                                            } else {
                                                sflag = 1;
                                                System.out.println("SPLIT 2:");
                                                split_function(st2hh, st2mm, st2ss, et2hh, et2mm, et2ss, split_file_name2);
                                            }
                                        }

                                        if (cntr == 3) {
                                            if (st3ss.length() == 0) {
                                            } else {
                                                sflag = 1;
                                                System.out.println("SPLIT 3:");
                                                split_function(st3hh, st3mm, st3ss, et3hh, et3mm, et3ss, split_file_name3);
                                            }
                                        }
                                        while (sflag == 1 && t2.isAlive()) {
                                        }

                                    }


                                    System.out.println("Thread count at end of split:" + Thread.activeCount());
                                } catch (Exception e) {

                                    e.printStackTrace();

                                }

                            }
                        });
                        t29.setPriority(2);
                        t29.start();



                    }
                });


            }////eof handle()
        });   /////convert_btn   SPLIT



        group2.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

            public void changed(ObservableValue<? extends Toggle> ov,
                    Toggle old_toggle, Toggle new_toggle) {
                if (group2.getSelectedToggle() != null) {

                    String str = group2.getSelectedToggle().getUserData().toString();
                    if (str.compareToIgnoreCase("default") == 0) {
                        format_flag = 0;
                        freeze_convert[0] = 0;
                        freeze_var = 0;
                          preset = " ";
                        for (int x = 0; x < 8; x++) {
                            if (freeze_convert[x] == 1) {
                                freeze_var = 1;
                                break;
                            }
                        }
                        if (freeze_var == 0) {
                            convert_btn.setDisable(true);
                        }
                    } else {
                        freeze_convert[0] = 1;
                        format_flag = 1;
                        format = str;
                        convert_btn.setDisable(false);
                        preset = " ";
                        if (str.compareTo("mp4h") == 0) {

                            format = "mp4";
                            preset = " -vcodec libx264 ";
                        }
                        if (str.compareTo("mp4") == 0) {

                            format = "mp4";
                            preset = " -vcodec libxvid ";
                        }


                    }
                    System.out.println(format_flag);


                }
            }
        });



        aspect_ratio_cb.valueProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue ov, String t, String t1) {

                System.out.print(t1);
                if (t1.compareToIgnoreCase("default") == 0) {

                    presets[0] = "";
                    freeze_convert[1] = 0;
                    freeze_var = 0;
                    for (int x = 0; x < 8; x++) {
                        if (freeze_convert[x] == 1) {
                            freeze_var = 1;
                            break;
                        }
                    }
                    if (freeze_var == 0) {
                        convert_btn.setDisable(true);
                    }


                } else {
                    convert_btn.setDisable(false);
                    presets[0] = t1;
                    freeze_convert[1] = 1;
                }
            }
        });


        videobit_rate_cb.valueProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue ov, String t, String t1) {

                System.out.print(t1);
                if (t1.compareToIgnoreCase("default") == 0) {
                    presets[1] = "";
                    freeze_convert[2] = 0;
                    freeze_var = 0;
                    for (int x = 0; x < 8; x++) {
                        if (freeze_convert[x] == 1) {
                            freeze_var = 1;
                            break;
                        }
                    }
                    if (freeze_var == 0) {
                        convert_btn.setDisable(true);
                    }


                } else {
                    presets[1] = t1;
                    convert_btn.setDisable(false);
                    freeze_convert[2] = 1;
                }
            }
        });


        frame_rate_cb.valueProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue ov, String t, String t1) {

                System.out.print(t1);
                if (t1.compareToIgnoreCase("default") == 0) {
                    presets[2] = "";
                    freeze_convert[3] = 0;
                    freeze_var = 0;
                    for (int x = 0; x < 8; x++) {
                        if (freeze_convert[x] == 1) {
                            freeze_var = 1;
                            break;
                        }
                    }
                    if (freeze_var == 0) {
                        convert_btn.setDisable(true);
                    }
                } else {
                    presets[2] = t1;
                    freeze_convert[3] = 1;
                    convert_btn.setDisable(false);
                }
            }
        });



        resolution_cb.valueProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue ov, String t, String t1) {

                System.out.print(t1);
                if (t1.compareToIgnoreCase("default") == 0) {
                    presets[3] = "";
                    freeze_convert[4] = 0;
                    freeze_var = 0;
                    for (int x = 0; x < 8; x++) {
                        if (freeze_convert[x] == 1) {
                            freeze_var = 1;
                            break;
                        }
                    }
                    if (freeze_var == 0) {
                        convert_btn.setDisable(true);
                    }
                } else {
                    presets[3] = t1;
                    freeze_convert[4] = 1;
                    convert_btn.setDisable(false);
                }
            }
        });



        audiobit_rate_cb.valueProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue ov, String t, String t1) {

                System.out.print(t1);
                if (t1.compareToIgnoreCase("default") == 0) {
                    presets[4] = "";
                    freeze_convert[5] = 0;
                    freeze_var = 0;
                    for (int x = 0; x < 8; x++) {
                        if (freeze_convert[x] == 1) {
                            freeze_var = 1;
                            break;
                        }
                    }
                    if (freeze_var == 0) {
                        convert_btn.setDisable(true);
                    }
                } else {
                    presets[4] = t1;
                    freeze_convert[5] = 1;
                    convert_btn.setDisable(false);
                }
            }
        });


        audio_channel_cb.valueProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue ov, String t, String t1) {

                System.out.print(t1);
                if (t1.compareToIgnoreCase("default") == 0) {
                    presets[5] = "";
                    freeze_convert[6] = 0;
                    freeze_var = 0;
                    for (int x = 0; x < 8; x++) {
                        if (freeze_convert[x] == 1) {
                            freeze_var = 1;
                            break;
                        }
                    }
                    if (freeze_var == 0) {
                        convert_btn.setDisable(true);
                    }
                } else {
                    presets[5] = t1;
                    freeze_convert[6] = 1;
                    convert_btn.setDisable(false);
                }
            }
        });

        audiosample_rate_cb.valueProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue ov, String t, String t1) {

                System.out.print(t1);
                if (t1.compareToIgnoreCase("default") == 0) {
                    presets[6] = "";
                    freeze_convert[7] = 0;
                    freeze_var = 0;
                    for (int x = 0; x < 8; x++) {
                        if (freeze_convert[x] == 1) {
                            freeze_var = 1;
                            break;
                        }
                    }
                    if (freeze_var == 0) {
                        convert_btn.setDisable(true);
                    }
                } else {
                    presets[6] = t1;
                    freeze_convert[7] = 1;
                    convert_btn.setDisable(false);
                }
            }
        });

        ////play button...
        btn4.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                String temp = list.getSelectionModel().getSelectedItem();
                String[] command = {"cmd", "/C", "ffplay -i \"" + temp + "\""};
                ProcessBuilder testing = new ProcessBuilder(command);
                testing.redirectErrorStream(true);


                try {

                    Process p = testing.start();



                    InputStreamReader isr = new InputStreamReader(p.getInputStream());
                    final BufferedReader br = new BufferedReader(isr);

                    Thread t = new Thread(new Runnable() {

                        public void run() {

                            String lineRead;

                            try {


                                while ((lineRead = br.readLine()) != null) {

                                    System.out.println(lineRead);
                                }



                            } catch (IOException e) {

                                e.printStackTrace();

                            } finally {

                                if (br != null) {

                                    try {

                                        br.close();

                                    } catch (IOException e) {

                                        e.printStackTrace();

                                    }

                                }

                            }

                        }
                    });
                    t.setPriority(7);
                    t.start();

                } catch (IOException e) {
                    e.printStackTrace(); // or log it, or otherwise handle it
                }





            }
        });  ///browse_btn set on action



///////Remuve button
        rem_btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                data.remove(list.getSelectionModel().getSelectedIndex());

                if (data.size() == 0) {
                    btn3.setDisable(true);
                }

            }
        });


        clear_btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                data.clear();
                btn3.setDisable(true);


                // System.out.println("1");
            }
        });






        list.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<String>() {

                    public void changed(ObservableValue<? extends String> ov,
                            String old_val, String new_val) {
                        btn3.setDisable(false);
                        play_vid = new_val;

                    }
                });



        Scene scene = new Scene(root, 1000, 500);//w  h



        convert_btn.setDisable(true);
        btn3.setDisable(true);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        scenetitle.getStyleClass().add("title");
        actiontarget.getStyleClass().add("progtext");
     //   pb_convert.getStyleClass().add("progbar");
        convert_btn.getStyleClass().add("convert_btn");//convert
        btn4.getStyleClass().add("btn4");//play
        btn3.getStyleClass().add("btn3");//split
        pb_convert.getStyleClass().add("pb_convert");
        grid.getStyleClass().add("root");

        primaryStage.setScene(scene);///////////////////////////////////
// primaryStage.setResizable(false);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public GridPane addGridPane() {
        grid = new GridPane();

        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new javafx.geometry.Insets(15, 15, 15, 15));
        scenetitle = new Text("Multimedia Transcoder");
        //      scenetitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));

        Text ar = new Text("Aspect-Ratio:");
        Text fr = new Text("Frame-Rate:");
        Text vbr = new Text("Video Bit-Rate:");
        Text res = new Text("Resolution:");
        Text abr = new Text("Audio Bit-Rate:");
        Text asr = new Text("Audio Sample-Rate:");
        Text ac = new Text("Audio Channels:");
        Text aud_formats = new Text("Audio Formats:");
        Text vid_formats = new Text("Video Formats:");



        //      aud_formats.setFont(Font.font("Verdana", FontWeight.NORMAL, 15));
        //     vid_formats.setFont(Font.font("Verdana", FontWeight.NORMAL, 15));

        ///  st1txt.setFont(new Font("Tahoma",10));



        aspect_ratio_cb.getItems().addAll(
                "16:9",
                "4:3", "Default");
        aspect_ratio_cb.setPromptText("Default");
        aspect_ratio_cb.setEditable(false);

        frame_rate_cb.getItems().addAll(
                "15",
                "20", "24", "25", "30", "60", "Default");
        frame_rate_cb.setPromptText("Default");
        frame_rate_cb.setEditable(false);



        videobit_rate_cb.getItems().addAll(
                "200", "350", "500", "700", "850", "1000", "1024", "Default");
        videobit_rate_cb.setPromptText("Default");
        videobit_rate_cb.setEditable(false);


        resolution_cb.getItems().addAll(
                "160x128", "290x240", "320x240", "360x240", "640x480", "720x576", "1024x768", "Default");
        resolution_cb.setPromptText("Default");
        resolution_cb.setEditable(false);

        audiobit_rate_cb.getItems().addAll("64", "128", "150", "160", "200", "Default");
        audiobit_rate_cb.setPromptText("Default");
        audiobit_rate_cb.setEditable(false);


        audiosample_rate_cb.getItems().addAll(
                "8000", "11025", "16000", "22050", "32000", "44100", "48000", "Default");
        audiosample_rate_cb.setPromptText("Default");
        audiosample_rate_cb.setEditable(false);



        audio_channel_cb.getItems().addAll(
                "1", "2", "Default");
        audio_channel_cb.setPromptText("Default");
        audio_channel_cb.setEditable(false);
        Image im1 = new Image("img2.png");
        iv1 = new ImageView();
        iv1.setImage(im1);

        grid.add(scenetitle, 0, 0);
        rb1.setSelected(true);
        grid.add(vid_formats, 3, 3);
        grid.add(rb1, 4, 4);//DEFAULTTTT
        grid.add(rb5, 4, 5);//mp4
        grid.add(rb2, 4, 6);//webm
        grid.add(rb3, 4, 7);//ogv


        grid.add(rb6, 5, 4);//avi
        grid.add(rb7, 5, 5);//mpeg
        grid.add(rb8, 5, 6);//wmv
        grid.add(rb9, 5, 7);//flv..

        grid.add(aud_formats, 6, 3);
        grid.add(rb4, 7, 4);//mp3 
        grid.add(rb10, 7, 5);//wav 

        grid.add(rb11, 4, 8);
        grid.add(list, 0, 2);////LIST VIEWWWW


        grid.add(btn3, 0, 4);//split button

        //   grid.add(btn4, 0, 3, 5, 5);//play
        grid.add(dest_folder_button, 1, 5);
//        dest_path.setFont(Font.font("Chunk Five", FontWeight.BOLD, 15));
        grid.add(dest_path, 0, 5);
        grid.add(actiontarget, 6, 15);
        grid.add(pb_convert, 3, 15);//progress
        grid.add(iv1, 0, 16);

        grid.add(cancel_button, 4, 16);
        grid.add(convert_btn, 2, 16);//convert

        //grid.setPrefWidth(10);



        grid.add(aspect_ratio_cb, 3, 1);//aspect ratio
        grid.add(ar, 3, 0);//aspect ratio

        grid.add(frame_rate_cb, 4, 1);//frame rate
        grid.add(fr, 4, 0);//frame rate


        grid.add(videobit_rate_cb, 5, 1);//vbit rate
        grid.add(vbr, 5, 0);//vbit rate

        grid.add(resolution_cb, 6, 1);//resltn
        grid.add(res, 6, 0);//resltn


        //    grid.add(resolution_cb, 5, 7);//resltn
        //  grid.add(res, 4, 7);//resltn


        grid.add(audiobit_rate_cb, 7, 1);//
        grid.add(abr, 7, 0);//

        grid.add(audiosample_rate_cb, 1, 1);//
        grid.add(asr, 1, 0);//

        grid.add(audio_channel_cb, 2, 1);//
        grid.add(ac, 2, 0);//


        grid.add(browse_btn, 1, 2, 1, 1);//browse 
        grid.add(rem_btn, 1, 2, 1, 4);
        grid.add(clear_btn, 1, 4);
        grid.add(btn4, 0, 3);//play

        btn4.setAlignment(Pos.BOTTOM_RIGHT);
        list.setMaxWidth(300);


        return grid;
    }//eo addgridpane

    public void transcode(int jj) {

        pb_convert.setProgress(0f);


        String temp = data.get(jj);
        list.getSelectionModel().select(jj);
        int i = 0, v = 0;
        for (i = temp.length() - 1; i > 0; i--) {
            if (temp.charAt(i) == '\\' || temp.charAt(i) == '/') {
                v = i;
                break;
            }
        }
        path = temp.substring(0, v + 1);
        inp = temp.substring(v + 1, temp.length());
        for (i = inp.length() - 1; i > 0; i--) {
            if (inp.charAt(i) == '.') {
                v = i;
                break;
            }
        }
        inp2 = inp.substring(0, v);
        if (format_flag == 0) {

            default_format = inp.substring(v + 1, inp.length());

            format = default_format;
        }

        System.out.println("Input:" + path);
        System.out.println(inp);
        System.out.println(inp2);
        System.out.println("Input format:" + format + "\n\n");





        date = new Date();


        op = output_path + inp2 + "(" + date.getDate() + "-" + (1 + date.getMonth()) + "_" + date.getHours() + "-" + date.getMinutes() + "-" + date.getSeconds() + ")" + "." + format;
        System.out.println("op is   " + op);
        //  String[] command = {"cmd", "/C", "ffmpeg.exe -i " + inp + " " + op};


        attribs = "";
        for (int countr = 0; countr < 7; countr++) {
            if (presets[countr].length() > 0) {
                attribs += pval[countr] + " " + presets[countr] + " ";
            }
        }

        String[] command2 = {cmnd1, cmnd2, "ffmpeg.exe -i \"" + temp + "\" " + attribs + preset + " \"" + op + "\""};
        
        System.out.println("ffmpeg.exe -i \"" + temp + "\" " + attribs + preset + " \"" + op + "\"");
        process_builder_var2 = new ProcessBuilder(command2);

        process_builder_var2.redirectErrorStream(true);
        // process_builder_var2.directory(new File(path));
        convert_btn.setDisable(true);
        btn3.setDisable(true);
        String[] command = {cmnd1, cmnd2, "ffmpeg.exe -i \"" + temp + "\""};
        process_builder_var1 = new ProcessBuilder(command);
        process_builder_var1.redirectErrorStream(true); // merge stdout, stderr of process
        //process_builder_var1.directory(new File(path));



        try {

            Process p = process_builder_var1.start();
            //  event.consume();


            InputStreamReader isr = new InputStreamReader(p.getInputStream());
            final BufferedReader br = new BufferedReader(isr);
            //RUN 1
            t = new Thread(new Runnable() {

                public void run() {
                    str ob2 = new str(0, 0, 0);
                    String lineRead;

                    try {
                        int flag = 0;
                        int h;
                        while ((lineRead = br.readLine()) != null) {

                            h = ob2.func(lineRead, "Duration");
                            if (h != -1) {
                                break;
                            }
                        }
                        int yy = 0;
                        for (yy = 0; yy < lineRead.length(); yy++) {
                            if (lineRead.charAt(yy) == ',') {
                                break;
                            }
                        }

                        System.out.println("LINEREAD SUBSTRING IS      " + lineRead.substring(0, yy));
                        duratn = lineRead.substring(0, yy).split(":");
                        hh = Integer.parseInt(duratn[1].trim());
                        mm = Integer.parseInt(duratn[2].trim());
                        ss = (int) Float.parseFloat(duratn[3].trim());
                        //          System.out.println("run1:  " + hh+","+mm+","+ss);
                        //  System.out.println("run1:  " + duratn[2].trim());
                        //    System.out.println("run1:  " + duratn[3].trim());

                        t2.setPriority(8);
                        t2.start();
                        //   t3.start();

                    } catch (IOException e) {

                        e.printStackTrace();

                    } finally {

                        if (br != null) {

                            try {

                                br.close();

                            } catch (IOException e) {

                                e.printStackTrace();

                            }

                        }

                    }

                }
            });
            t.setPriority(7);
            t.start();

        } catch (IOException e) {
            e.printStackTrace(); // or log it, or otherwise handle it
        }




        try {

            final Process p = process_builder_var2.start();

            //  event.consume();
            InputStreamReader isr = new InputStreamReader(p.getInputStream());
            final BufferedReader br = new BufferedReader(isr);


            //RUN 2
            t2 = new Thread(new Runnable() {

                public void run() {


                    actiontarget.setFill(Color.RED);
                    float temp, prgs;
                    System.out.println(" hh=" + hh + "  mm=" + mm + "   ss=" + ss);
                    str obj = new str(hh, mm, ss);
                    //   System.out.print("hh="+hh+"mm="+mm);          
                    String lineRead, stt;
                    int h = 0, hhh = 0;
                    int yy;
                    try {


                        while ((lineRead = br.readLine()) != null) {


System.out.println(lineRead);
                            status = lineRead;
                            h = 0;
                            hhh = 0;


                            h = obj.func(lineRead, "time");
                            hhh = obj.func(lineRead, "bitrate");
                            //String mm=;
                            if (h != -1 && hhh != -1) {
                                stt = status.substring(h, hhh);
                                stt.trim();
                                // System.out.println(stt);

                                for (yy = 0; yy < stt.length(); yy++) {
                                    if (stt.charAt(yy) == '=') {
                                        break;
                                    }

                                }
                                stt = stt.substring(yy + 1, stt.length());
                                String sty[] = new String[5];
                                sty = stt.split(":");
                                phh = Integer.parseInt(sty[0]);
                                pmm = Integer.parseInt(sty[1]);
                                psss = Float.parseFloat(sty[2]);
                                pss = (int) psss;
                                temp = obj.func2(phh, pmm, pss);
                                if (temp > 0) {

                                    System.out.println(temp);
                                    pb_convert.setProgress(temp);
                                    prgs = ((temp) * 100);

                                    actiontarget.setText("" + new DecimalFormat("##.##").format(prgs) + "%");
                                }
                                //   System.out.println();
                                //   actiontarget.setText());
                                ////


                            }
                            //  System.out.println(status);
                        }

                        pb_convert.setProgress(1.0f);



                        Color dark_green = Color.rgb(0, 255, 0);
                        actiontarget.setFill(dark_green);

                        actiontarget.setText("100%");

                        System.out.println("done :))");

                        pb_convert.setProgress(0f);
                        actiontarget.setText("");
                        convert_btn.setDisable(false);
                        btn3.setDisable(false);


                    } catch (IOException e) {

                        e.printStackTrace();

                    } finally {

                        if (br != null) {

                            try {

                                br.close();

                            } catch (IOException e) {

                                e.printStackTrace();

                            }

                        }

                    }

                }
            });

            //   t2.start();


        } catch (IOException e) {
            e.printStackTrace(); // or log it, or otherwise handle it
        }



    }/////eo transcode

    public void split_init() {


        split_output_path = split_dest_path.getText();
        time_diff_obj = new GetDuration();
        String temp = play_vid;


        int i = 0, v = 0;
        for (i = temp.length() - 1; i > 0; i--) {
            if (temp.charAt(i) == '\\' || temp.charAt(i) == '/') {
                v = i;
                break;
            }
        }
        path = temp.substring(0, v + 1);
        inp = temp.substring(v + 1, temp.length());
        for (i = inp.length() - 1; i > 0; i--) {
            if (inp.charAt(i) == '.') {
                v = i;
                break;
            }
        }
        inp2 = inp.substring(0, v);

        if (split_format_flag == 0) {

            split_format = inp.substring(v + 1, inp.length());



        }


        System.out.println("inp path" + path);
        System.out.println("inp=" + inp);
        System.out.println("abs file name" + inp2);
        System.out.println("format:" + split_format);







        split_attribs = "";
        for (int cntr = 0; cntr < 7; cntr++) {
            if (split_presets[cntr].length() > 0) {
                split_attribs += split_pval[cntr] + " " + split_presets[cntr] + " ";
            }
        }

    }

    public void split_function(String stime1, String stime2, String stime3, String etime1, String etime2, String etime3, String splitfilename) {


        if (splitfilename.length() == 0) {
            date = new Date();
            op = split_output_path + inp2 + "(" + date.getDate() + "-" + (1 + date.getMonth()) + "_" + date.getHours() + "-" + date.getMinutes() + "-" + date.getSeconds() + ")" + "." + split_format;

        } else {
            op = split_output_path + splitfilename + "." + split_format;


        }



        time_diff = time_diff_obj.func(stime1, stime2, stime3, etime1, etime2, etime3);

        String durtn2[] = new String[5];
        durtn2 = time_diff.split(":");
        hh = Integer.parseInt(durtn2[0].trim());
        mm = Integer.parseInt(durtn2[1].trim());
        ss = (int) Float.parseFloat(durtn2[2].trim());




        String[] command2 = {cmnd1, cmnd2, "ffmpeg -i \"" + play_vid + "\" -ss " + stime1 + ":" + stime2 + ":" + stime3 + " -t " + time_diff + " " + split_attribs + s_preset + " \"" + op + "\""};

        process_builder_var2 = new ProcessBuilder(command2);

        process_builder_var2.redirectErrorStream(true);

        //  process_builder_var2.directory(new File(path));
        btn3.setDisable(true);
        split_main_btn.setDisable(true);



        try {

            final Process p = process_builder_var2.start();


            InputStreamReader isr = new InputStreamReader(p.getInputStream());
            final BufferedReader br = new BufferedReader(isr);


            //RUN 2
            t2 = new Thread(new Runnable() {

                public void run() {


                    split_actiontarget.setFill(Color.RED);
                    float temp, prgs;

                    str obj4 = new str(hh, mm, ss);
                    //   System.out.print("hh="+hh+"mm="+mm);          
                    String lineRead, stt;
                    int h = 0, hhh = 0;
                    int yy;
                    try {


                        while ((lineRead = br.readLine()) != null) {



                            status = lineRead;
                            h = 0;
                            hhh = 0;


                            h = obj4.func(lineRead, "time");
                            hhh = obj4.func(lineRead, "bitrate");
                            //String mm=;
                            if (h != -1 && hhh != -1) {
                                stt = status.substring(h, hhh);
                                stt.trim();
                                // System.out.println(stt);

                                for (yy = 0; yy < stt.length(); yy++) {
                                    if (stt.charAt(yy) == '=') {
                                        break;
                                    }

                                }
                                stt = stt.substring(yy + 1, stt.length());
                                String sty[] = new String[5];
                                sty = stt.split(":");
                                phh = Integer.parseInt(sty[0]);
                                pmm = Integer.parseInt(sty[1]);
                                psss = Float.parseFloat(sty[2]);
                                pss = (int) psss;
                                temp = obj4.func2(phh, pmm, pss);
                                if (temp > 0) {

                                    System.out.println(temp);
                                    pb_split.setProgress(temp);
                                    prgs = ((temp) * 100);

                                    split_actiontarget.setText("" + new DecimalFormat("##.##").format(prgs) + "%");
                                }



                            }

                        }


                        split_actiontarget.setFill(Color.GREEN);
                        pb_split.setProgress(1.0f);
                        split_actiontarget.setText("100% Split");

                        System.out.println("done split :))");

                        try {
                            t2.sleep(1400);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        pb_split.setProgress(0f);

                        btn3.setDisable(false);
                        split_main_btn.setDisable(false);
                    } catch (IOException e) {

                        e.printStackTrace();

                    } finally {

                        if (br != null) {

                            try {

                                br.close();

                            } catch (IOException e) {

                                e.printStackTrace();

                            }

                        }

                    }

                }
            });
            t2.setPriority(9);
            t2.start();


        } catch (IOException e) {
            e.printStackTrace(); // or log it, or otherwise handle it
        }


    }
}
