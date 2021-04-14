package org.dps.admin
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_dashboard_teacher.*
import org.dps.admin.ui.fragments.CreateFragment
import org.dps.admin.ui.fragments.HomeFragment
import org.dps.admin.ui.fragments.NotificationFragment

class DashboardActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_teacher)


        with(main_viewpager) {
            adapter = MainPagerAdapter(supportFragmentManager)
            offscreenPageLimit = 3
            addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrollStateChanged(state: Int) = Unit
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) = Unit

                override fun onPageSelected(position: Int) {
                    main_bottom_navigation.menu.getItem(position).isChecked = true
                }
            })
        }

        main_bottom_navigation.getOrCreateBadge(R.id.action_notifications).apply {
            backgroundColor = Color.RED
            badgeTextColor = Color.WHITE
            maxCharacterCount = 3
            number = 103
            isVisible = true
        }

        main_bottom_navigation.getOrCreateBadge(R.id.action_create).apply {
            backgroundColor = Color.RED
            isVisible = true
        }
        

        main_bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.action_home ->{
                    main_viewpager.currentItem = 0
                }
                R.id.action_create -> {
                    main_viewpager.currentItem = 1
                }
                R.id.action_notifications -> {
                    main_viewpager.currentItem = 3
                }
                R.id.action_profile -> {
                    main_viewpager.currentItem = 4
                }
                R.id.action_more -> {
                    main_viewpager.currentItem = 5
                }
            }
            true
        }
    }


    inner class MainPagerAdapter(fm: FragmentManager) :
        FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 ->  HomeFragment.instance()
                1 -> CreateFragment.instance()
                2 -> HomeFragment.instance()
                3 -> NotificationFragment.instance()
                4 -> HomeFragment.instance()
                else -> HomeFragment.instance()
            }
        }

        override fun getCount() = 5
    }
}
