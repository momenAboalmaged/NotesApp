package com.example.notesapp.ui.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.notesapp.R;
import com.example.notesapp.data.SharedPref;
import com.example.notesapp.databinding.FragmentHomeBinding;
import com.example.notesapp.databinding.FragmentProfileBinding;
import com.example.notesapp.db.NoteDB;
import com.example.notesapp.models.Note;
import com.example.notesapp.ui.NotesFactoryActivity;
import com.example.notesapp.ui.RegisterActivity;
import com.example.notesapp.ui.SplashScreen;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    private FragmentProfileBinding binding;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        binding = FragmentProfileBinding.bind(view);
        binding.userName.setText(getNameFROMSharedPref());
        binding.userEmail.setText(getEmailFromSharedPref());
        binding.userPhone.setText(getPhoneFROMSharedPref());

        binding.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAccDialog();
            }
        });
        binding.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(binding.getRoot())
                        .navigate(R.id.action_profileFragment_to_editUserDataFragment);
            }
        });
        return binding.getRoot();

    }

    public String getNameFROMSharedPref() {
        return SharedPref.getNameFromSharedPref(getActivity());
    }
    public String getEmailFromSharedPref() {
        return SharedPref.getEmailFromSharedPref(getActivity());
    } public String getPhoneFROMSharedPref() {
        return SharedPref.getPhoneFromSharedPref(getActivity());
    }

    public void deleteAccDialog(){

        AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
        builder1.setTitle("Delete Account !");
        builder1.setMessage("Are u sure delete your acc");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        SharedPref.deletePref(getContext());
                        deleteRoom();
                        startActivity(new Intent(getActivity(),SplashScreen.class));
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    private void deleteRoom() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                NoteDB noteDB = NoteDB.getInstance(getContext());
                noteDB.getNoteDao().deleteAll();
            }

        }).start();
        Toast.makeText(getContext(), "data deleted successfully", Toast.LENGTH_SHORT).show();
    }

}