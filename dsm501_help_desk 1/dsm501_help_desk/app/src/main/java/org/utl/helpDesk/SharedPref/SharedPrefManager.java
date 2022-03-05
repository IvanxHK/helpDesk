package org.utl.helpDesk.SharedPref;

import android.content.Context;
import android.content.SharedPreferences;

import org.utl.helpDesk.model.Employee;
import org.utl.helpDesk.model.User;

public class SharedPrefManager {
    private static final String SHARED_PREFERENCES = "SHARED_PREFERENCES";
    private static final String SHARED_PREFERENCES_ID_USER="SHARED_PREFERENCES_ID_USER";
    private static final String SHARED_PREFERENCES_USER_NAME="SHARED_PREFERENCES_USER_NAME";
    private static final String SHARED_PREFERENCES_PASSWORD="SHARED_PREFERENCES_PASSWORD";
    private static final String SHARED_PREFERENCES_ID_EMPLOYEE="SHARED_PREFERENCES_ID_EMPLOYEE";

    //Definimos los objetos que vamos a requerir para almacenar nuestras sharedPref
    private static SharedPrefManager instance;
    private Context context;
    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPreferences;

    private SharedPrefManager(Context context){
        this.context=context;
        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES,Context.MODE_PRIVATE);
        editor= sharedPreferences.edit();
    }

    public static synchronized SharedPrefManager getInstance(Context context){
        if (instance==null){
            instance=new SharedPrefManager(context);
        }
        return instance;
    }

    public void saveUser(User user){
        editor.putInt(SHARED_PREFERENCES_ID_USER, user.getId());
        editor.putString(SHARED_PREFERENCES_USER_NAME, user.getUserName());
        editor.putString(SHARED_PREFERENCES_PASSWORD, user.getPassword());
        editor.putInt(SHARED_PREFERENCES_ID_EMPLOYEE, user.getEmployee().getId());
        editor.apply();
    }
    public User getUsers(){
        Employee employee=new Employee();
        employee.setId(sharedPreferences.getInt(SHARED_PREFERENCES_ID_EMPLOYEE, -1));
        User user=new User(
                sharedPreferences.getInt(SHARED_PREFERENCES_ID_USER, -1),
                sharedPreferences.getString(SHARED_PREFERENCES_USER_NAME, null),
                sharedPreferences.getString(SHARED_PREFERENCES_PASSWORD, null),
                employee
        );
        return user;
    }
}
