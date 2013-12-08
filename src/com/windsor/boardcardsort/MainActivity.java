package com.windsor.boardcardsort;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	
	//list of UNSORTED boardin Mcards
	private ArrayList<BoardingCard> listOfBoardCards;
	//list of SORTED boarding cards
	private ArrayList<BoardingCard> sortedList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		listOfBoardCards = new ArrayList<BoardingCard>();
		//init the boarding card list
		//init();
		
		Button btnAddCard = (Button) findViewById(R.id.btnAddCard);
		Button btnSort = (Button) findViewById(R.id.btnSort);
		btnAddCard.setOnClickListener(this);
		btnSort.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true; 
	}
	
	public void onClick(View v){

        switch(v.getId())
        {
            case R.id.btnAddCard:
            {
            	LayoutInflater factory = LayoutInflater.from(this);

            	//text_entry is a Layout XML file containing two text field to display in alert dialog
            	final View textEntryView = factory.inflate(R.layout.text_entry, null);

            	final EditText transportMeans = (EditText) textEntryView.findViewById(R.id.EditText1); 
            	final EditText departure = (EditText) textEntryView.findViewById(R.id.EditText2);
            	final EditText destination = (EditText) textEntryView.findViewById(R.id.EditText3);
            	final EditText seatRes = (EditText) textEntryView.findViewById(R.id.EditText4);
            	final EditText baggage = (EditText) textEntryView.findViewById(R.id.EditText5);


            	transportMeans.setText("", TextView.BufferType.EDITABLE);
            	departure.setText("", TextView.BufferType.EDITABLE);
            	destination.setText("", TextView.BufferType.EDITABLE);
            	seatRes.setText("", TextView.BufferType.EDITABLE);
            	baggage.setText("", TextView.BufferType.EDITABLE);

            	final AlertDialog.Builder alert = new AlertDialog.Builder(this);
            	alert.setIcon(android.R.drawable.ic_input_add).setTitle("Add BoardingCard:").setView(textEntryView).setPositiveButton("Save",
            	  new DialogInterface.OnClickListener() {
            	   public void onClick(DialogInterface dialog,
            	     int whichButton) {

            	    Log.i("AlertDialog","TextEntry 1 Entered " + transportMeans.getText().toString());
            	    Log.i("AlertDialog","TextEntry 2 Entered " + departure.getText().toString());
            	    Log.i("AlertDialog","TextEntry 3 Entered " + destination.getText().toString());
            	    Log.i("AlertDialog","TextEntry 4 Entered " + seatRes.getText().toString());
            	    Log.i("AlertDialog","TextEntry 5 Entered " + baggage.getText().toString());
            	    
            	    String textViewContent = transportMeans.getText().toString() + " - " + departure.getText().toString() + " > " + destination.getText().toString();
            	    AddRow(textViewContent);
            		//System.out.println("ID: " + id);
            	    listOfBoardCards.add(new BoardingCard( textViewContent, transportMeans.getText().toString(), departure.getText().toString(), destination.getText().toString(), seatRes.getText().toString(), true) );

            	    /* User clicked OK so do some stuff */
            	   }
            	  }).setNegativeButton("Cancel",
            	  new DialogInterface.OnClickListener() {
            	   public void onClick(DialogInterface dialog,
            	     int whichButton) {
            	     /*
            	     * User clicked cancel so do some stuff
            	     */
            	   }
            	  });
            	alert.show();
                break;
            }
            case R.id.btnSort:
            {
            	startSorting(v);
                break;
            }

        }
    }
	
	public void startSorting(View v){
		//listOfBoardCards.add(new BoardingCard("", "FLIGHT", "BARCELONA", "MADRID", "3A", true));
		//listOfBoardCards.add(new BoardingCard("", "BUS", "LONDON", "MANCHESTER", "SK3", true));
		//listOfBoardCards.add(new BoardingCard("", "BUS", "NEW_YORK", "CANADA", "NY123", true));
		//listOfBoardCards.add(new BoardingCard("", "FLIGHT", "MADRID", "LONDON", "KML12", true));
		//listOfBoardCards.add(new BoardingCard("", "FLIGHT", "MANCHESTER", "NEW_YORK", "ER32", true));
		
		ArrayList<BoardingCard> sortedListLocal = new ArrayList<BoardingCard>();
		ArrayList<BoardingCard> listOfBoardCardsLocal = listOfBoardCards;

		
		// directly print the ArrayList
		for(int i=0; i < listOfBoardCardsLocal.size(); i++ ){
			System.out.println("Unorder List"); 
			System.out.println(listOfBoardCardsLocal.get(i).getTransportMeans() + ", " + listOfBoardCardsLocal.get(i).getDeparture() + ", " + listOfBoardCardsLocal.get(i).getDestination() + ", " + listOfBoardCardsLocal.get(i).getSeat() + ", " + listOfBoardCardsLocal.get(i).getBaggage()); 
		}
		
		Sorting obj = new Sorting(listOfBoardCardsLocal);
		//sortedList = new ArrayList<BoardingCard>();
		sortedListLocal.addAll(obj.sortBoardingCard());
		sortedList = sortedListLocal;
		
		System.out.println("RETURN ORDERLIST" + sortedListLocal);
		System.out.println("RETURN UNORDERLIST" + listOfBoardCardsLocal);

		// directly print the ArrayList
		for(int i=0; i < sortedListLocal.size(); i++ ){
			
			//clear current tablerows and create new order list
			reCreateRows(v);
			System.out.println("Order List of Size " + sortedListLocal.size());
			System.out.println(sortedListLocal.get(i).getTransportMeans() + ", " + sortedListLocal.get(i).getDeparture() + ", " + sortedListLocal.get(i).getDestination() + ", " + sortedListLocal.get(i).getSeat() + ", " + sortedListLocal.get(i).getBaggage());
		}
		
		if(listOfBoardCardsLocal.isEmpty()){
			System.out.println("UNORDERLIST IS EMPTY " + listOfBoardCardsLocal);
		}
		
		if(sortedListLocal.isEmpty()){
			System.out.println("ORDERLIST IS EMPTY " + sortedListLocal);
		}
		

	}
	
	public void reCreateRows(View v){
		//clear all rows
    	TableLayout tl = (TableLayout) findViewById(R.id.table);
    	tl.removeAllViews();
		
		for(int i=0; i < sortedList.size(); i++ ){
			AddRow(sortedList.get(i).getTbRowLink());
		}
		TextView mytextview = (TextView) findViewById(R.id.txtSort);
		mytextview.setText("List Is Sorted!");
		
		Button btnAddCard = (Button) findViewById(R.id.btnAddCard);
		Button btnSort = (Button) findViewById(R.id.btnSort);
		btnAddCard.setEnabled(false);
		btnSort.setEnabled(false);
	}
	
	public void AddRow(String textValue){
		//open screen
        //Intent i = new Intent(this, login.class);
        //startActivity(i);
    	System.out.println("btnAddCrd");
    	/* Find Tablelayout defined in main.xml */
    	TableLayout tl = (TableLayout) findViewById(R.id.table);
    	/* Create a new row to be added. */
    	TableRow tr = new TableRow(this);
    	tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
    	tr.setOnLongClickListener(OnLongClickTableRow);

    	/* Create a TextView to be the row-content. */            	
    	TextView a = new TextView(this);
    	//a.setText(getResources().getString(R.string.textView));
    	a.setText(textValue);
    	a.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1f));

    	/* Create a Button to be the row-content. */            	
    	Button b = new Button(this);
    	b.setText(getResources().getString(R.string.btnMore));
    	b.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
    	b.setOnClickListener(OnClickMoreBtn);
    	/* Add Button to row. */
    	tr.addView(a);
    	tr.addView(b);
    	/* Add row to TableLayout. */
    	//tr.setBackgroundResource(R.drawable.sf_gradient_03);
    	tl.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
   	}
	
	private OnLongClickListener OnLongClickTableRow = new OnLongClickListener() {

        @Override
        public boolean onLongClick(View v) {
        	PopUp(v);
        	return false;
        }
    };
    
	private OnClickListener OnClickMoreBtn = new OnClickListener() {
		@Override
		public void onClick(View v) {
			//switch (v.getId()) {
				//case R.id.btnMore:
				//
				TableRow tr = (TableRow) v.getParent();
				TextView textView = (TextView)tr.getChildAt(0);
				String value = textView.getText().toString();
				System.out.println("Value In TextView " + value);
				//break;
			//}
		}
    };
    
    public void PopUp(final View v){
    	new AlertDialog.Builder(this)
    	.setTitle("Delete Row")
    	.setMessage("You have choosen to delete this row?")
    	.setIcon(android.R.drawable.ic_dialog_alert)
    	.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

    		
    	    public void onClick(DialogInterface dialog, int whichButton) {
    	    	// row is your row, the parent of the clicked button
                View row = v;
                // container contains all the rows, you could keep a variable somewhere else to the container which you can refer to here
                ViewGroup container = ((ViewGroup)row.getParent());
                // delete the row and invalidate your view so it gets redrawn
                container.removeView(row);
                container.invalidate();
    	        Toast.makeText(MainActivity.this, "TableRow Deleted", Toast.LENGTH_SHORT).show();
    	    }})
    	 .setNegativeButton(android.R.string.no, null).show();
    }

}
