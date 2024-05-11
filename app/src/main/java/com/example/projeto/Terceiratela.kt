package com.example.projeto

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.example.projeto.databinding.ActivityTerceiratelaBinding
import java.io.File

class Terceiratela : AppCompatActivity() {

    private lateinit var captureImageView: ImageView
    private lateinit var imageUrl: Uri
    private lateinit var binding: ActivityTerceiratelaBinding

    private val contract = registerForActivityResult(ActivityResultContracts.TakePicture())
    {
        imageUrl = createImageUri()
        captureImageView.setImageURI(imageUrl)
        shareImage(imageUrl)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTerceiratelaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cpf = intent.getStringExtra("CPF")
        binding.txtTitle.text = cpf

        imageUrl = createImageUri()
        captureImageView = binding.CaptureImageView
        val captureImgBtn = binding.btTake
        captureImgBtn.setOnClickListener {
            contract.launch(imageUrl)
        }

        val uploadButton = binding.btUpload
        uploadButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, PICK_IMAGE_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
            val selectedImageUri = data?.data
            if (selectedImageUri != null) {
                binding.CaptureImageView.setImageURI(selectedImageUri)
                shareImage(selectedImageUri)
            }
        }
    }

    private fun shareImage(imageUri: Uri) {
        val shareIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_STREAM, imageUri)
            type = "image/jpeg"
        }
        startActivity(Intent.createChooser(shareIntent, "Compartilhar imagem..."))
    }

    private fun createImageUri():Uri{
        val image = File(filesDir, "camera_photos.png")
        return FileProvider.getUriForFile(this,
            "com.example.projeto.Terceiratela",
            image)
    }

    companion object {
        private const val PICK_IMAGE_REQUEST_CODE = 100
    }
}
