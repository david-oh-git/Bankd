package io.osemwota.bankd.ui.login

import android.content.res.Configuration
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.progressSemantics
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import io.osemwota.bankd.R
import io.osemwota.bankd.ui.theme.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavController? = null,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    val viewState by viewModel.state.collectAsState()
    viewState.customerId?.let {
        navController?.navigate("home/$it")
        Timber.d("Login successful !")
    }
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val notImplementedMsg = stringResource(id = R.string.not_yet_implemented)

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { paddingValues ->

        val emptyUserNameWarning = stringResource(R.string.login_screen_enter_username)
        val emptyPasswordWarning = stringResource(R.string.login_screen_enter_password)

        viewState.snackbarMessage?.let {
            showSnackBar(
                scope,
                snackbarHostState,
                it
            )
            viewModel.sendAction(LoginAction.DismissSnackBar)
        }

        LoginContent(
            modifier = Modifier.padding(paddingValues),
            state = viewState,
            onUserNameChange = {
                viewModel.sendAction(LoginAction.UsernameChanged(it))
            },
            onPasswordChange = {
                viewModel.sendAction(LoginAction.PasswordChanged(it))
            },
            onLoginButtonClick = {
                                 if (isUserNameInValid(viewState)) {
                                     viewModel.sendAction(LoginAction.InvalidUsername(emptyUserNameWarning))
                                     return@LoginContent
                                 }else if (isPasswordInValid(viewState)) {
                                     viewModel.sendAction(LoginAction.InvalidPassword(emptyPasswordWarning))
                                     return@LoginContent
                                 }else {
                                     viewModel.sendAction(LoginAction.LoginButtonClicked)
                                 }
            },
            onCreateAccountButtonClick = {
                viewModel.sendAction(LoginAction.ShowSnackBar(notImplementedMsg))
            }
        )

    }

}

private fun isUserNameInValid(
    viewState: LoginViewState
): Boolean {
    return viewState.username.isBlank() && viewState.username.length < 5
}

private fun isPasswordInValid(
    viewState: LoginViewState
): Boolean {
    return viewState.password.isBlank() && viewState.password.length < 5
}

private fun showSnackBar(
    scope: CoroutineScope,
    snackbarHostState: SnackbarHostState,
    message: String,
    snackbarDuration: SnackbarDuration = SnackbarDuration.Short
) {
    scope.launch {
        snackbarHostState.showSnackbar(
            message = message,
            duration = snackbarDuration
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginContent(
    modifier: Modifier = Modifier,
    state: LoginViewState = LoginViewState(),
    onUserNameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginButtonClick: () -> Unit,
    onCreateAccountButtonClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .padding(start = 20.dp, end = 20.dp)
            .fillMaxSize()
    ) {
        var passwordVisibility by remember{ mutableStateOf(false) }

        Text(
            modifier = modifier
                .padding(top = 88.dp),
            text = stringResource(R.string.login_screen_welcome_msg),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = modifier.background(color = MaterialTheme.colorScheme.surface.copy(
                alpha = 0.3f
            )),
            text = "Login to continue",
            fontSize = 20.sp,
            color = Color.Gray
        )
        Image(
            painter = painterResource(id = R.drawable.logo_default),
            contentDescription = stringResource(R.string.login_screen_bank_logo_desc),
            modifier = modifier
                .size(200.dp)
                .align(Alignment.CenterHorizontally)
                .padding(top = 20.dp, bottom = 20.dp),
            alignment = Alignment.Center
        )
        TextField(
            modifier = modifier
                .padding(top = 8.dp)
                .fillMaxWidth(),
            value = state.username,
            onValueChange = onUserNameChange,
            maxLines = 1,
            label = { Text(text = stringResource(R.string.login_screen_email_field_label)) },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = MaterialTheme.colorScheme.background
            ),
            enabled = !state.isLoading
        )
        TextField(
            modifier = modifier
                .fillMaxWidth(),
            value = state.password,
            onValueChange = onPasswordChange,
            maxLines = 1,
            label = { Text(text = stringResource(R.string.login_screen_password_field_label)) },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = MaterialTheme.colorScheme.background
            ),
            trailingIcon = {
                Text(
                    modifier = modifier.clickable { passwordVisibility = !passwordVisibility },
                    text = if(passwordVisibility) stringResource(
                        R.string.login_screen_password_field_trailing_hide
                    ) else stringResource(
                        R.string.login_screen_password_field_trailing_show
                    ),
                    color = Green50,
                    fontSize = 12.sp
                )
            },
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            enabled = !state.isLoading
        )

        Text(
            modifier = modifier.padding(top = 16.dp),
            text = stringResource(R.string.login_screen_forgot_password),
            color = Green50
        )
        val buttonColor = if (!state.isLoading) ButtonDefaults.buttonColors() else ButtonDefaults.buttonColors(
            containerColor = Blue95
        )
        Button(
            modifier = modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(top = 32.dp),
            shape = RoundedCornerShape(8.dp),
            onClick = onLoginButtonClick,
            colors = buttonColor,
            contentPadding = PaddingValues(16.dp),
        ) {
            if (state.isLoading) {
                ProgressIndicator(color = Blue48)
            }else {
                Text(
                    text = stringResource(R.string.login_screen_login_btn_txt),
                    fontSize = 16.sp ,
                )
            }
        }

        if (!state.isLoading) {
            Button(
                modifier = modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 32.dp),
                shape = RoundedCornerShape(8.dp),
                onClick = onCreateAccountButtonClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Blue95
                ),
                contentPadding = PaddingValues(16.dp),
            ) {
                Text(
                    text = stringResource(R.string.login_screen_create_account_btn),
                    fontSize = 16.sp ,
                    color = Blue48
                )
            }
        }

    }
}

@Composable
fun ProgressIndicator(
    size: Dp = 32.dp,
    sweepAngle: Float = 90f,
    color: Color = MaterialTheme.colorScheme.primary,
    strokeWidth: Dp = ProgressIndicatorDefaults.CircularStrokeWidth
) {
    val transition = rememberInfiniteTransition()
    val currentArcStartAngle by transition.animateValue(
        initialValue = 0,
        targetValue = 360,
        typeConverter = Int.VectorConverter,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1100,
                easing = LinearEasing
            )
        )
    )

    val stroke = with(LocalDensity.current) {
        Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Square)
    }

    Canvas(
        modifier = Modifier
            .progressSemantics()
            .size(size)
            .padding(strokeWidth / 2)
        ) {

        drawCircle(Color.LightGray, style = stroke)

        drawArc(
            color,
            startAngle = currentArcStartAngle.toFloat() - 90,
            sweepAngle = sweepAngle,
            useCenter = false,
            style = stroke
        )
    }


}

@Preview(
    showBackground = true,
    name = "Light Mode"
)
@Preview(
    showBackground = true,
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun LoginPreview() {
    BankdTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            LoginScreen()
        }
    }
}