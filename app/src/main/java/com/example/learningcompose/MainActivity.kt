package com.example.learningcompose


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.EnterTransition.Companion.None
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learningcompose.ui.theme.LearningComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearningComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    login()
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun login(){

    var username by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }

        Box(modifier = Modifier.fillMaxSize()) {

            Image(painter = painterResource(id = R.drawable.tesla_img_bg), contentDescription = "Login",
            modifier = Modifier
                .fillMaxSize()
                .blur(6.dp),
            contentScale = ContentScale.Crop)

            Box(modifier = Modifier
                .fillMaxSize()
                .padding(28.dp)
                .clip(CutCornerShape(16.dp))
                .alpha(0.6f)
                .background(Color.White))
                     {

            }
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(48.dp),
                 horizontalAlignment = Alignment.CenterHorizontally,
                 verticalArrangement = Arrangement.SpaceAround) {
                loginHeader()
                loginFields(username,password, onUsernameChange = {username=it}, onPasswordChange = {password=it}, onforgotpasswordclick= {})
                loginFooter(onsigninclick= {}, onsignupclick= {})
            }
        }
}

@SuppressLint("ModifierFactoryUnreferencedReceiver")
private fun Modifier.background(color: androidx.compose.ui.graphics.Color, function: () -> Unit) {

}

@Composable
fun loginHeader(){
    Column(horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Welcome", fontSize = 36.sp, fontWeight = FontWeight.ExtraBold)
        Text(text = "Sign In to Continue", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
fun loginFields(username: String, password: String, onUsernameChange: (String) -> Unit, onPasswordChange: (String) -> Unit, onforgotpasswordclick: () -> Unit ){

    Column() {
        demoField(value = username, label = "Username", placeholder = "Enter your username", onValueChange = onUsernameChange,
            leadingIcon = {
                Icon(Icons.Default.Email, contentDescription = "email")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Go))

        Spacer(modifier = Modifier.height(8.dp))

        demoField(value = password, label = "Password", placeholder = "Enter your password", onValueChange = onPasswordChange,
            visualTransformation = PasswordVisualTransformation('\u2022'),
            leadingIcon = {
                Icon(Icons.Default.Lock, contentDescription = "password")
            },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Go)
        )

        TextButton(onClick = onforgotpasswordclick, modifier = Modifier.align(Alignment.End)) {
            Text(text = "Forgot Password?")
        }
    }
}


@Composable
fun loginFooter(
    onsigninclick: ()->Unit,
    onsignupclick: ()->Unit
){

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick =  onsigninclick, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Sign In")
        }

        TextButton(onClick = onsignupclick) {
            Text(text = "Don't have an account? Click Here")
        }
    }
}

@Composable
fun demoField(value: String,
              label: String, placeholder: String,
              visualTransformation: VisualTransformation = VisualTransformation.None,
              keyboardOptions: KeyboardOptions=KeyboardOptions.Default,
              leadingIcon: @Composable (()-> Unit)? = null,
              trailingIcon: @Composable (()-> Unit)? = null,
              onValueChange: (String)-> Unit){
    OutlinedTextField(value = value, onValueChange = onValueChange,
    label={
        Text(text = label)
    },
    placeholder={
        Text(text = placeholder)
    },
    visualTransformation = visualTransformation,
    keyboardOptions = keyboardOptions,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon
    )
}



