package com.ingesup.cocktail.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

/**
 * Created by lopes_f on 12/3/2014.
 * <florian.lopes@outlook.com>
 */
public class DialogUtils {

	public static AlertDialog confirmDialog(Activity context, int titleResId, int questionResId, int yesAnswerResId,
											int noAnswerResId, DialogInterface.OnClickListener positiveListener,
											DialogInterface.OnClickListener negativeListener) {

		AlertDialog.Builder builder = new AlertDialog.Builder(context)
				.setTitle(titleResId)
				.setMessage(questionResId)
				.setPositiveButton(yesAnswerResId, positiveListener)
				.setNegativeButton(noAnswerResId, negativeListener);

		return builder.create();
	}
}