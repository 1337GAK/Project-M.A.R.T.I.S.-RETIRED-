package martis.project.martischaniaupdated.Fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import martis.project.martischaniaupdated.R;

/**
 * Created by GAK on 4/3/2016.
 */
public class Settings extends Fragment {

    private String message1;
    private String message2;
    private String message3;
    private String message5;
    private String message4;
    private RadioGroup skinTonerg;
    private EditText editText4;
    Button saveAll;
    Button clearAll;

    private TextView setAge;
    private TextView setWeight;
    private TextView setHeight;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Settings");
        View view = inflater.inflate(R.layout.fragment_settings,container,false);
        SharedPreferences userDetails = Settings.this.getActivity().getSharedPreferences("userdetails", Context.MODE_PRIVATE);
        String textAge = userDetails.getString("ageInput", "");
        String textWeight = userDetails.getString("weightInput", "");
        String textHeight = userDetails.getString("heightInput", "");
        String textSkin = userDetails.getString("skinInput","1");
        String SUV = userDetails.getString("UVInput", "5");
        skinTonerg = (RadioGroup) view.findViewById(R.id.skinTone);

        setAge = (TextView) view.findViewById(R.id.setage);
        setAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAgePopup();
            }
        });
        setWeight = (TextView) view.findViewById(R.id.setweight);
        setWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWeightPopup();
            }
        });

        setHeight = (TextView) view.findViewById(R.id.setheight);
        setHeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHeightPopup();
            }
        });

        if (textSkin.equals("1")){
            skinTonerg.check(skinTonerg.getChildAt(0).getId());
        }
        else if (textSkin.equals("2")){
            skinTonerg.check(skinTonerg.getChildAt(1).getId());
        }
        else if (textSkin.equals("3")){
            skinTonerg.check(skinTonerg.getChildAt(2).getId());
        }
        else if (textSkin.equals("4")){
            skinTonerg.check(skinTonerg.getChildAt(3).getId());
        }
        else if (textSkin.equals("5")){
            skinTonerg.check(skinTonerg.getChildAt(4).getId());
        }
        else if (textSkin.equals("6")){
            skinTonerg.check(skinTonerg.getChildAt(5).getId());
        }

        setAge= (TextView) view.findViewById(R.id.setage);
        if (textAge.equals("")){
            setAge.setText("1");
        }else{
            setAge.setText(textAge);
        }
        setWeight= (TextView) view.findViewById(R.id.setweight);
        if (textWeight.equals("")){
            setWeight.setText("1");
        }else {
            setWeight.setText(textWeight);
        }
        setHeight= (TextView) view.findViewById(R.id.setheight);
        if (textHeight.equals("")){
            setHeight.setText("1");
        }else{
            setHeight.setText(textHeight);
        }
        editText4= (EditText) view.findViewById(R.id.UV);
        editText4.setText(SUV);
        int pos;
        pos= skinTonerg.indexOfChild(view.findViewById(skinTonerg.getCheckedRadioButtonId()));
        Log.i("Error", "skinPosition = " + pos);
        int integerSkinType=pos+1;

        saveAll = (Button) view.findViewById(R.id.saveAll);
        saveAll.setOnClickListener(new View.OnClickListener() {


            public void onClick(View v)
                {
                    message1= (String) setAge.getText();
                    message2= (String) setWeight.getText();
                    message3= (String) setHeight.getText();

                  //  message4=String.valueOf(integerSkinType);
                    message4=Integer.toString(4);
                    message5 = editText4.getText().toString();
                   SharedPreferences userDetails = Settings.this.getActivity().getSharedPreferences("userdetails",Context.MODE_PRIVATE);
                   SharedPreferences.Editor edit = userDetails.edit();
                   edit.putString("ageInput", message1);
                    edit.putString("weightInput", message2);
                    edit.putString("heightInput", message3);
                    edit.putString("skinInput", message4);
                    edit.putString("UVInput", message5);
                    edit.commit();
                    Toast.makeText(Settings.this.getActivity(), message4 ,Toast.LENGTH_SHORT).show();

            }
        });

        clearAll = (Button) view.findViewById(R.id.clearAll);
        clearAll.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                SharedPreferences userDetails =Settings.this.getActivity().getSharedPreferences("userdetails",Context.MODE_PRIVATE);  //clear preferences
                SharedPreferences.Editor edit = userDetails.edit();
                edit.clear();
                edit.putString("ageInput", "");
                edit.putString("weightInput", "");
                edit.putString("heightInput", "");
                edit.putString("skinInput", "1");
                edit.putString("UVInput", "1");
                edit.commit();
                setAge.setText("");
                setWeight.setText("");
                setHeight.setText("");
                skinTonerg.check(skinTonerg.getChildAt(0).getId());
            }
        });
        return view;
    }

    protected void showAgePopup(){
        LayoutInflater layoutInflater= LayoutInflater.from(Settings.this.getActivity());
        View promptView =layoutInflater.inflate(R.layout.pop_up_window, null);
        AlertDialog.Builder alertDialogBuilder= new AlertDialog.Builder(Settings.this.getActivity());
        alertDialogBuilder.setView(promptView);
        final NumberPicker numPick=(NumberPicker) promptView.findViewById(R.id.numberPicker);
        numPick.setMinValue(0);
        numPick.setMaxValue(100);
        //setup a dialog window
        alertDialogBuilder.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                message1=String.valueOf(numPick.getValue());
                setAge.setText(message1);
                Toast.makeText(Settings.this.getActivity(), "asd", Toast.LENGTH_SHORT).show();
               // Toast.makeText(Settings.this, "Age="+numPick.getValue(), Toast.LENGTH_SHORT).show();
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert= alertDialogBuilder.create();
        alert.show();
    }

    protected void showWeightPopup(){
        LayoutInflater layoutInflater= LayoutInflater.from(Settings.this.getActivity());
        View promptView =layoutInflater.inflate(R.layout.pop_up_window, null);
        AlertDialog.Builder alertDialogBuilder= new AlertDialog.Builder(Settings.this.getActivity());
        alertDialogBuilder.setView(promptView);
        final NumberPicker numPick=(NumberPicker) promptView.findViewById(R.id.numberPicker);
        numPick.setMinValue(0);
        numPick.setMaxValue(150);
        numPick.setWrapSelectorWheel(true);
        numPick.setOnLongPressUpdateInterval(2000);
        //setup a dialog window
        alertDialogBuilder.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                message2=String.valueOf(numPick.getValue());
                setWeight.setText(message2);
                Toast.makeText(Settings.this.getActivity(), "Weight="+numPick.getValue(), Toast.LENGTH_SHORT).show();
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert= alertDialogBuilder.create();
        alert.show();
    }

    protected void showHeightPopup(){
        LayoutInflater layoutInflater= LayoutInflater.from(Settings.this.getActivity());
        View promptView =layoutInflater.inflate(R.layout.pop_up_window, null);
        AlertDialog.Builder alertDialogBuilder= new AlertDialog.Builder(Settings.this.getActivity());
        alertDialogBuilder.setView(promptView);
        final NumberPicker numPick=(NumberPicker) promptView.findViewById(R.id.numberPicker);
        numPick.setMinValue(0);
        numPick.setMaxValue(230);
        //setup a dialog window
        alertDialogBuilder.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                message3=String.valueOf(numPick.getValue());
                setHeight.setText(message3);
                Toast.makeText(Settings.this.getActivity(), "Height="+numPick.getValue(), Toast.LENGTH_SHORT).show();
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert= alertDialogBuilder.create();
        alert.show();
    }

}
