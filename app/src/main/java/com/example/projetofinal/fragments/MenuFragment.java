package com.example.projetofinal.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.projetofinal.R;
import com.example.projetofinal.activities.RankingActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MenuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MenuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MenuFragment newInstance(String param1, String param2) {
        MenuFragment fragment = new MenuFragment();
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
        View root = inflater.inflate(R.layout.fragment_menu, container, false);

        LinearLayout forcaLayout = root.findViewById(R.id.llForca);
        forcaLayout.setOnClickListener(v -> {
//            startActivity(new Intent(getActivity(), CategoriaActivity.class));
            new CategoriaDialogFragment().show(requireActivity().getSupportFragmentManager(), "Categoria Modal");
        });

        LinearLayout adicionarPalavraLayout = root.findViewById(R.id.llAdicionarPalavra);
        adicionarPalavraLayout.setOnClickListener(v -> {
            new AdicionarPalavraDialogFragment().show(requireActivity().getSupportFragmentManager(), "Adicionar Palavra Modal");

        });

        LinearLayout rankingLayout = root.findViewById(R.id.llRanking);
        rankingLayout.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), RankingActivity.class));
        });

        return root;
    }

}