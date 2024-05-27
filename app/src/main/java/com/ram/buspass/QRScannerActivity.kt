package com.ram.buspass
// QRScannerActivity.kt

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult

class QRScannerActivity : ComponentActivity() {

    private val cameraPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            startQRScanner()
        } else {
            Toast.makeText(this, "Camera permission is required to scan QR codes", Toast.LENGTH_SHORT).show()
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestCameraPermission()
    }

    private fun requestCameraPermission() {
        cameraPermissionRequest.launch(Manifest.permission.CAMERA)
    }

    private fun startQRScanner() {
        IntentIntegrator(this).apply {
            setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
            setPrompt("Scan a QR code")
            setCameraId(0)  // Use a specific camera of the device
            setBeepEnabled(false)
            setBarcodeImageEnabled(true)
            initiateScan()
        }
    }
    private fun isBankAppInstalled(): Boolean {
        val packageManager = applicationContext.packageManager
        val intent = packageManager.getLaunchIntentForPackage("com.example.bankapp") // Replace with the actual package name of the bank app
        return intent != null
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val result: IntentResult? = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        result?.let {
            if (it.contents != null) {
                // Here, you can perform the action related to the scanned QR code
                // For example, you can open a specific URI or launch another activity
                val scannedResult = it.contents
                if (isBankAppInstalled()) {
                    // Replace "bankapp://payment" with the actual URI scheme of the bank app
                    val bankAppUri = Uri.parse("bankapp://payment")
                    val intent = Intent(Intent.ACTION_VIEW, bankAppUri)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Bank app is not installed", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Failed to scan QR code", Toast.LENGTH_SHORT).show()
            }
        }
        finish()
    }

}

