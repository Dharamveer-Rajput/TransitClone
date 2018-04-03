package com.transitclone.items;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.TextView;


import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IItem;
import com.mikepenz.fastadapter.items.AbstractItem;
import com.mikepenz.fastadapter_extensions.drag.IDraggable;
import com.mikepenz.materialize.holder.StringHolder;
import com.transitclone.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class EditPackItem extends AbstractItem<EditPackItem, EditPackItem.ViewHolder> implements IDraggable<EditPackItem, IItem> {

    public String header;
    public StringHolder name;
    public StringHolder description;



    @Override
    public boolean isSelected() {
        return isSelected;
    }

    public boolean isSelected;


    private boolean mIsDraggable = true;

    public EditPackItem withHeader(String header) {
        this.header = header;
        return this;
    }

    public EditPackItem withName(String Name) {
        this.name = new StringHolder(Name);
        return this;
    }

    public EditPackItem withName(@StringRes int NameRes) {
        this.name = new StringHolder(NameRes);
        return this;
    }

    public EditPackItem withDescription(String description) {
        this.description = new StringHolder(description);
        return this;
    }

    public EditPackItem withDescription(@StringRes int descriptionRes) {
        this.description = new StringHolder(descriptionRes);
        return this;
    }

    @Override
    public boolean isDraggable() {
        return mIsDraggable;
    }

    @Override
    public EditPackItem withIsDraggable(boolean draggable) {
        this.mIsDraggable = draggable;
        return this;
    }

    public EditPackItem withIsSelected(boolean b) {
        isSelected = b;
        return this;
    }

    /**
     * defines the type defining this item. must be unique. preferably an id
     *
     * @return the type
     */
    @Override
    public int getType() {
        return R.id.fastadapter_sample_item_id;
    }

    /**
     * defines the layout which will be used for this item in the list
     *
     * @return the layout for this item
     */
    @Override
    public int getLayoutRes() {
        return R.layout.row_edit_pack;
    }

    @Override
    public ViewHolder getViewHolder(@NonNull View v) {
        return new ViewHolder(v);
    }


    /**
     * our ViewHolder
     */
    public static class ViewHolder extends FastAdapter.ViewHolder<EditPackItem> {
        protected View view;





        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.view = view;
        }

        @Override
        public void bindView(@NonNull EditPackItem item, @NonNull List<Object> payloads) {
            //get the context
            Context ctx = itemView.getContext();

            //set the background for the item
            //  UIUtils.setBackground(view, FastAdapterUIUtils.getSelectableBackground(ctx, Color.RED, true));

            //set the text for the name
            // StringHolder.applyTo(item.name, tvNumber);
            //set the text for the description or hide
            // StringHolder.applyToOrHide(item.description, tvDate);
        }

        @Override
        public void unbindView(@NonNull EditPackItem item) {
            //tvBox.setText(null);
            //tvDate.setText(null);
        }
    }
}
