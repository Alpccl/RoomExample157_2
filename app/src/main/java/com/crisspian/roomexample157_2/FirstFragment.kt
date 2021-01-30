package com.crisspian.roomexample157_2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.crisspian.roomexample157_2.databinding.FragmentFirstBinding
import com.crisspian.roomexample157_2.model.TaskDataBase
import com.crisspian.roomexample157_2.model.TaskEntity
import com.crisspian.roomexample157_2.viewModel.TaskViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private lateinit var binding : FragmentFirstBinding
    private val viewModel: TaskViewModel by activityViewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.allTask.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.textviewFirst.text = it.toString()
            }
        })

        val tarea = TaskEntity(title = "Nueva", description = "Descripcion", author = "Cristian")

        viewModel.insertTask(tarea)



        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }
}