package com.hungrok.mygallery

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_photo.*



/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [PhotoFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [PhotoFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */

private const val ARG_URI = "uri" // 정적 전역상수
// 코틀린에서 정적멤버 (비객체적 사용)는 class 바깥에 위치 합니다 (Java 는 class 내부 static 으로 선언)

class PhotoFragment : Fragment() {

    private var listener: OnFragmentInteractionListener? = null
    private var uri: String? = null // ? = nullable , 코틀린에서 모든 data 는 null 이 불가능 합니다 만...

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

            uri = it.getString(ARG_URI)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_photo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // imageView.setImageURI(Uri.parse(uri)) // 이 방법 사용하면 texture too big 과 memory 부족으로 결과..
        // Glide 라이브러리 사용방법 (이미지를 비동기로 로딩, 미사용자원 자동해제 에 용이)
        Glide.with(this).load(uri).into(imageView)

        // Activity 에게 통지 - optional code
        notifyToeventListener(Uri.parse(uri))

    }

    // TODO: Rename method, update argument and hook method into UI event
    fun notifyToeventListener(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) { // instanceOf (java 상속관계 test)
            listener = context  // Activiy 객체를 리스너로 등록한다
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object { // 코틀린 - 정적메소드 가져가는 방법 (Java 호환성 위하여)
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PhotoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(uri : String) =     // factory method 객체 생성방법
            PhotoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_URI,uri)
                }
            }
    }

    /* JAVA code 참고
    public static PhotoFragment newInstance(String imageUrl) { // factory method 객체생성 방법
        Bundle args = new Bundle();
        args.putString(ARG_URI, imageUrl);
        PhotoFragment fragment = new PhotoFragment();
        fragment.setArguments(args) ; // 객체생성시 매개변수 일반화
        return fragment;
    }
    */
}
