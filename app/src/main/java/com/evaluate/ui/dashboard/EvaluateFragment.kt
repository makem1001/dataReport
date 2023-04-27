package com.evaluate.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v4.app.Fragment
import android.arch.lifecycle.ViewModelProvider
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Button
import com.evaluate.databinding.BasicInfoLayoutBinding
import com.evaluate.databinding.FragmentEvaluateBinding
import com.evaluate.databinding.Step2LayoutBinding
import com.evaluate.databinding.Step3LayoutBinding

class EvaluateFragment : Fragment(), ItemsAdapter.OnNextClickListener {

    private var _binding: FragmentEvaluateBinding? = null
    private var itemsAdapter: ItemsAdapter? = null
    private var items1: List<Item>? = null
    private var items2: List<Item>? = null
    private var items3: List<ItemCheck>? = null
    private var symptomList: RecyclerView? = null
    private var symptomListCheck: RecyclerView? = null
    private var fillWeatherBtn: Button? = null
    private var nextStepBtn: Button? = null
    private var weatherInfoView:View?=null
    private var startEvalutionBtn:Button?=null

    private var step2Layout:Step2LayoutBinding?=null
    private var step3Layout: Step3LayoutBinding?=null


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            ).get(EvaluateViewModel::class.java)

        _binding = FragmentEvaluateBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val basicInfoView: BasicInfoLayoutBinding = binding.basicInfoLayout
        step2Layout = binding.step2Layout
        symptomList = step2Layout?.symptomList
        symptomListCheck = step2Layout?.symptomListCheck
        fillWeatherBtn = step2Layout?.fillWeatherBtn
        nextStepBtn = binding.nextStepBtn
        weatherInfoView = step2Layout?.weatherInfoLayout
        startEvalutionBtn = step2Layout?.startEvaluationBtn

        step3Layout = binding.step3Layout

        items1 = listOf(
            Item(1, "1.头疼？"),
            Item(1, "无"),
            Item(1, "头痛不明显，无痛苦表情，服一般止痛药后有所缓解，不影响日常活动"),
            Item(1, "头痛较轻，有痛苦表情，服一般止痛药后有明显好转，不影响日常活动"),
            Item(1, "头痛较重，有痛苦表情，服一般止痛药后有所缓解，影响日常活动"),
            Item(1, "头痛较重，不能忍受，卧床不起，服一般止痛药无效"),
            Item(1, "下一题")
        )

        items2 = listOf(
            Item(2, "2.呕吐？"),
            Item(2, "无"),
            Item(2, "每日呕吐1-2次，呕吐物以食物为主，服一般止吐药物有明显好转，不影响日常活动"),
            Item(2, "每日呕吐3-4次，最有呕吐物为胃液，服一般止吐药物有所缓解，影响日常活动"),
            Item(2, "每日呕吐5次以上，卧床不起，服用一般止吐药无效"),
            Item(2, "下一题")
        )

        items3 = listOf(
            ItemCheck("3.其他症状？"),
            ItemCheck("气短"),
            ItemCheck("胸闷"),
            ItemCheck("眼花"),
            ItemCheck("失眠"),
            ItemCheck("嗜睡"),
            ItemCheck("食欲减退"),
            ItemCheck("腹胀"),
            ItemCheck("腹泻"),
            ItemCheck("便秘"),
            ItemCheck("口唇发绀"),
            ItemCheck("手足发麻1"),
            ItemCheck("手足发麻2"),
            ItemCheck("手足发麻3")
        )

        itemsAdapter = ItemsAdapter(items1!!, this)
        symptomList?.adapter = itemsAdapter
        var layoutManager = LinearLayoutManager(this.context)
        var layoutManager1 = LinearLayoutManager(this.context)
        symptomList?.layoutManager = layoutManager

        symptomListCheck?.adapter = ItemsCheckAdapter(items3!!)
        symptomListCheck?.layoutManager = layoutManager1

        nextStepBtn?.setOnClickListener(View.OnClickListener {
            Log.d("zhanfei", "onClick...")
            basicInfoView.root.visibility = View.GONE
            step2Layout?.root?.visibility = View.VISIBLE
            nextStepBtn?.visibility = View.GONE
        })

        fillWeatherBtn?.setOnClickListener(View.OnClickListener {
            weatherInfoView?.visibility=View.VISIBLE
            symptomListCheck?.visibility = View.GONE
            fillWeatherBtn?.visibility = View.GONE
            startEvalutionBtn?.visibility=View.VISIBLE
        })

        startEvalutionBtn?.setOnClickListener(View.OnClickListener {
            weatherInfoView?.visibility=View.GONE
            startEvalutionBtn?.visibility=View.GONE
            step2Layout?.root?.visibility=View.GONE
            step3Layout?.root?.visibility=View.VISIBLE
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onRefreshDatas() {
        if (itemsAdapter?.getItemStep()!! > 1) {
            symptomListCheck?.visibility = View.VISIBLE
            symptomList?.visibility = View.GONE
            fillWeatherBtn?.visibility = View.VISIBLE
        } else {
            items2?.let { itemsAdapter?.setItems(it) }
        }

    }
}