package yasa.step.matapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

// a
public class MainActivity3 extends AppCompatActivity {

    private static final String TAG = "MainActivity3";
    private FirebaseFirestore db;

    private TextView pricesTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        // Initialize Firebase
        FirebaseApp.initializeApp(this);
        db = FirebaseFirestore.getInstance();

        pricesTextView = findViewById(R.id.pricesTextView);

        // Call the function to get and display prices
        displayPrices();

    }

    private void displayPrices() {
        db.collection("prices")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@NonNull QuerySnapshot queryDocumentSnapshots, @NonNull FirebaseFirestoreException e) {
                        if (e != null) {
                            Log.w(TAG, "Listen failed.", e);
                            return;
                        }

                        StringBuilder pricesBuilder = new StringBuilder();
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                            if (document.exists()) {
                                // Try retrieving price as a Double
                                Double price = document.getDouble("price");
                                if (price != null) {
                                    pricesBuilder.append(price.toString()).append("\n");
                                } else {
                                    // Handle the case where price is null or not a Double
                                    Log.w(TAG, "Field 'price' is null or not a number in document: " + document.getId());
                                }
                            }
                        }

                        // Update TextView with prices
                        pricesTextView.setText(pricesBuilder.toString());
                    }
                });
    }
}