package com.dit599.editorUI;



import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class FloorItemsActivity extends ListActivity {
  public void onCreate(Bundle icicle) {
    super.onCreate(icicle);
    String[] values = new String[] { "Enemies", "Weapones", "Potions" };
    // use your custom layout
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
        R.layout.flooritem, R.id.label, values);
    setListAdapter(adapter);
  }

  @Override
  protected void onListItemClick(ListView l, View v, int position, long id) {
    String item = (String) getListAdapter().getItem(position);
    Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
    Intent openStart1 = new Intent("com.dit599.editorUI.ItemTypesActivity");
    startActivity(openStart1);
  }
} 

