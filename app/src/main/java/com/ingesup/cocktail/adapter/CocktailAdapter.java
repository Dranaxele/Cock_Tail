package com.ingesup.cocktail.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.ingesup.cocktail.R;
import com.ingesup.cocktail.metier.Cocktail;
import com.ingesup.cocktail.service.CocktailServiceFactory;
import com.ingesup.cocktail.utils.FavouriteCocktailUtil;

import java.util.List;

/**
 * Created by lopes_f on 11/19/2014.
 * <florian.lopes@outlook.com>
 */
public class CocktailAdapter extends BaseAdapter {

	private Context context;
	private List<Cocktail> cocktails;

	private LayoutInflater layoutInflater;

	// view to use for item
	private View itemLayout;

	public CocktailAdapter(Context context, List<Cocktail> cocktails) {
		this.context = context;
		this.cocktails = cocktails;

		this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return cocktails != null ? cocktails.size() : 0;
	}

	@Override
	public Object getItem(int position) {
		return cocktails.get(position);
	}

	@Override
	public long getItemId(int position) {
		return cocktails.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;

		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = layoutInflater.inflate(R.layout.cocktail_item, parent, false);

			viewHolder.cocktailName = (TextView) convertView.findViewById(R.id.cocktailItemNameTextView);
			viewHolder.cocktailColor = (TextView) convertView.findViewById(R.id.cocktailItemColorTextView);
			viewHolder.favouriteImage = (ImageView) convertView.findViewById(R.id.favouriteItemImageView);

			convertView.setTag(viewHolder);

		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		Cocktail cocktail = cocktails.get(position);
		if (cocktail != null) {
			viewHolder.cocktailName.setText(cocktail.getNom());
			viewHolder.cocktailColor.setText(cocktail.getCouleur());

			if (CocktailServiceFactory.instance(context).findFavourites().contains(cocktail)) {
				viewHolder.favouriteImage.setImageResource(android.R.drawable.star_on);
			}
		}

		return convertView;
	}

	private static class ViewHolder {

		TextView cocktailName;
		TextView cocktailColor;

		ImageView favouriteImage;
	}
}
