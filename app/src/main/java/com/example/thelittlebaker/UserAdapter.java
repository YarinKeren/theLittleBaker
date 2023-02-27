package com.example.thelittlebaker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter  extends ArrayAdapter {
    List<User> buffer=new ArrayList<>();

    public UserAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.buffer.addAll(objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null)
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        TextView user,pass;
        CheckBox isAdmin;
        Button deleteUser;

        user=convertView.findViewById(R.id.lvuser);
        pass=convertView.findViewById(R.id.lvpass);
        isAdmin=convertView.findViewById(R.id.lvcheck);
        deleteUser=convertView.findViewById(R.id.deleteUserBtn2);

        if (buffer.get(position).getAcecssLevel()==1)
            deleteUser.setVisibility(View.INVISIBLE);
        else
            isAdmin.setVisibility(View.INVISIBLE);

        user.setText(buffer.get(position).getUserName());
        pass.setText(buffer.get(position).getPassword());
        isAdmin.setEnabled(false);
        isAdmin.setChecked(buffer.get(position).getAcecssLevel()==1);

        deleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseHelper.deleteUser(buffer.get(position));
                notifyDataSetChanged();
            }
        });

        return  convertView;

    }
}
