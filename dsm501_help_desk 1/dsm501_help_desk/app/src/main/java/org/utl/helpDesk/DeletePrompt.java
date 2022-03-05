package org.utl.helpDesk;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DeletePrompt extends DialogFragment {

    public interface OnDelete{
        void delete();
    }

    private OnDelete onDelete;

    public DeletePrompt(OnDelete onDelete) {
        this.onDelete = onDelete;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Eliminar")
                .setMessage("Desea eliminar el registro")
                .setPositiveButton("Si", (DialogInterface dialog, int id) -> {
                    onDelete.delete();
                })
                .setNegativeButton("No", (DialogInterface dialog, int id) -> {
                    Toast.makeText(getActivity(), "No eliminado", Toast.LENGTH_SHORT).show();
                });
        return builder.create();
    }
}

