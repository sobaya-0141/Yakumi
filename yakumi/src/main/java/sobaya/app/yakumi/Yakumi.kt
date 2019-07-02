package sobaya.app.yakumi

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.yakumi_main.view.*

class Yakumi @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val onScrollChangeListener = YakumiScrollListener()

    private var totalScrollY: Int = 0
    private var scrollerHeight: Int = 0
    private var recyclerView: RecyclerView? = null
    private var currentAnimator: ObjectAnimator? = null

    companion object {
        private const val BUBBLE_ANIMATION_DURATION_HIDE: Long = 1000
    }

    init {
        LayoutInflater.from(context).inflate(R.layout.yakumi_main, this, true)
        yakumiText.visibility = View.GONE

        orientation = HORIZONTAL
        clipChildren = false
    }

    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        super.onSizeChanged(width, height, oldWidth, oldHeight)

        scrollerHeight = height
    }

    override fun onDetachedFromWindow() {
        recyclerView?.removeOnScrollListener(onScrollChangeListener)
        super.onDetachedFromWindow()
    }

    fun setRecyclerView(recyclerView: RecyclerView) {
        this.recyclerView = recyclerView
        this.recyclerView?.addOnScrollListener(onScrollChangeListener)
    }

    private fun showBubble() {
        currentAnimator?.cancel()
        yakumiText.alpha = 1f
        yakumiText.visibility = View.VISIBLE
    }

    private fun hideBubble() {
        currentAnimator?.cancel()
        currentAnimator = ObjectAnimator.ofFloat(yakumiText, "alpha", 1f, 0f).apply {
            duration = BUBBLE_ANIMATION_DURATION_HIDE
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
                    hide()
                }

                private fun hide() {
                    yakumiText.visibility = View.GONE
                    currentAnimator = null
                }
            })
            start()
        }
    }

    private inner class YakumiScrollListener : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                hideBubble()
            } else {
                showBubble()
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            totalScrollY += dy

            recyclerView.let {
                if (it.adapter is YakumiAdapter) {
                    val layoutmanager = recyclerView.layoutManager
                    val position = if (layoutmanager is LinearLayoutManager) {
                        if (dy > 0) {
                            layoutmanager.findLastVisibleItemPosition()
                        } else {
                            layoutmanager.findFirstVisibleItemPosition()
                        }
                    } else if (layoutmanager is GridLayoutManager) {
                        if (dy > 0) {
                            layoutmanager.findLastVisibleItemPosition()
                        } else {
                            layoutmanager.findFirstVisibleItemPosition()
                        }
                    } else {
                        throw NoSupportLayoutManagerException("only LinearLayoutManager or GridLayoutManager")
                    }

                    val bubbleText = (it.adapter as YakumiAdapter).setYakumiText(position)
                    yakumiText.text = bubbleText
                    yakumiText.y = calculateY(recyclerView)
                }
            }
        }
    }

    private fun calculateY(recyclerView: RecyclerView): Float {
        with (recyclerView) {
            return if ((this@Yakumi).yakumiText != null) {
                val rate = computeVerticalScrollOffset().toFloat() / (computeVerticalScrollRange() - computeVerticalScrollExtent())
                (scrollerHeight - (this@Yakumi).yakumiText.height) * rate
            } else {
                0f
            }
        }
    }

    interface YakumiAdapter {
        fun setYakumiText(position: Int): String
    }
}