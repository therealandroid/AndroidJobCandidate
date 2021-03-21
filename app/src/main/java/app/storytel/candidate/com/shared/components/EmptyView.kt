package app.storytel.candidate.com.shared.components

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import app.storytel.candidate.com.R
import kotlinx.android.synthetic.main.empty_view_layout.view.*


class EmptyView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    init {
        inflate(context, R.layout.empty_view_layout, this)

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.EmptyView)

        val title: TextView = findViewById(R.id.empty_view_title)
        val icon: ImageView = findViewById(R.id.empty_view_icon)

        icon.setImageDrawable(attributes.getDrawable(R.styleable.EmptyView_emptyViewIcon))
        title.text = attributes.getString(R.styleable.EmptyView_emptyViewTitle)
        empty_view_retry_button.text = attributes.getString(R.styleable.EmptyView_emptyViewButtonText)
        empty_view_description.text = attributes.getString(R.styleable.EmptyView_emptyViewDescription)
        attributes.recycle()
    }

}