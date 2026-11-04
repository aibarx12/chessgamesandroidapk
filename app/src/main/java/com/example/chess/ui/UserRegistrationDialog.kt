package com.example.chess.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.chess.data.BookDatabase
import com.example.chess.gamification.AuthResult
import com.example.chess.gamification.GameScoreRecord
import com.example.chess.gamification.PlayerAvatar
import com.example.chess.gamification.RegisteredUser
import com.example.chess.gamification.UserProgressState
import com.example.ui.theme.AmberStar
import com.example.ui.theme.CharcoalDark
import com.example.ui.theme.CharcoalSurface
import com.example.ui.theme.EmeraldWin
import com.example.ui.theme.GoldDark
import com.example.ui.theme.GoldLight
import com.example.ui.theme.GoldPrimary
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun UserRegistrationDialog(
    userProgress: UserProgressState,
    initialTab: Int = 0, // 0 = Perfil/Registro, 1 = Puntuaciones Individuales, 2 = Jugadores
    onRegisterUser: (username: String, emailOrTag: String, pin: String, avatar: PlayerAvatar, migrateGuest: Boolean) -> AuthResult,
    onLoginUser: (userId: String, pin: String) -> AuthResult,
    onLogout: () -> Unit,
    onSelectAvatar: (PlayerAvatar) -> Unit,
    onOpenGameById: (String) -> Unit,
    onDismiss: () -> Unit
) {
    var selectedTab by remember { mutableIntStateOf(initialTab.coerceIn(0, 2)) }
    var showCreateNewForm by remember(userProgress.isRegistered) {
        mutableStateOf(!userProgress.isRegistered)
    }

    var usernameInput by remember { mutableStateOf("") }
    var emailInput by remember { mutableStateOf("") }
    var pinInput by remember { mutableStateOf("") }
    var selectedAvatar by remember(userProgress.currentAvatar) { mutableStateOf(userProgress.currentAvatar) }
    var migrateGuest by remember { mutableStateOf(true) }
    var feedbackMessage by remember { mutableStateOf<String?>(null) }
    var isErrorFeedback by remember { mutableStateOf(false) }

    // Estado para pedir PIN al cambiar a un usuario protegido
    var pendingLoginUser by remember { mutableStateOf<RegisteredUser?>(null) }
    var loginPinAttempt by remember { mutableStateOf("") }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Card(
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(containerColor = CharcoalDark),
            border = BorderStroke(1.2.dp, GoldPrimary.copy(alpha = 0.65f)),
            modifier = Modifier
                .fillMaxWidth(0.94f)
                .fillMaxHeight(0.88f)
                .testTag("user_registration_dialog")
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                // Cabecera dorada
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Brush.horizontalGradient(
                                colors = listOf(Color(0xFF332014), Color(0xFF1E130D))
                            )
                        )
                        .padding(horizontal = 16.dp, vertical = 14.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painter = painterResource(id = userProgress.currentAvatar.drawableRes),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(44.dp)
                                    .clip(CircleShape)
                                    .border(2.dp, GoldPrimary, CircleShape)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(
                                    text = if (userProgress.isRegistered) {
                                        userProgress.displayUsername
                                    } else {
                                        "Registro de Jugador"
                                    },
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontWeight = FontWeight.ExtraBold,
                                        color = GoldLight
                                    )
                                )
                                Text(
                                    text = "${userProgress.totalXp} pts • ${userProgress.rank.title}",
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        color = Color(0xFFD8C8B4)
                                    )
                                )
                            }
                        }

                        IconButton(onClick = onDismiss) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Cerrar",
                                tint = Color(0xFFD8C8B4)
                            )
                        }
                    }
                }

                // Pestañas de navegación
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF1A110C))
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    DialogTabChip(
                        label = "Perfil",
                        icon = Icons.Default.Person,
                        selected = selectedTab == 0,
                        onClick = {
                            selectedTab = 0
                            feedbackMessage = null
                        },
                        modifier = Modifier.weight(1f)
                    )
                    DialogTabChip(
                        label = "Puntos (${userProgress.individualGameScores.size})",
                        icon = Icons.Default.EmojiEvents,
                        selected = selectedTab == 1,
                        onClick = {
                            selectedTab = 1
                            feedbackMessage = null
                        },
                        modifier = Modifier.weight(1f)
                    )
                    DialogTabChip(
                        label = "Cuentas (${userProgress.registeredUsers.size})",
                        icon = Icons.Default.Group,
                        selected = selectedTab == 2,
                        onClick = {
                            selectedTab = 2
                            feedbackMessage = null
                        },
                        modifier = Modifier.weight(1f)
                    )
                }

                // Mensaje de éxito / error
                feedbackMessage?.let { msg ->
                    Surface(
                        color = if (isErrorFeedback) Color(0xFF4A1818) else EmeraldWin.copy(alpha = 0.18f),
                        border = BorderStroke(
                            1.dp,
                            if (isErrorFeedback) Color(0xFFEF5350) else EmeraldWin
                        ),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 14.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = msg,
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = if (isErrorFeedback) Color(0xFFFFB4AB) else Color(0xFFA7F3D0),
                                fontWeight = FontWeight.SemiBold
                            ),
                            modifier = Modifier.padding(10.dp)
                        )
                    }
                }

                // Contenido principal de la pestaña
                Box(modifier = Modifier.weight(1f)) {
                    when (selectedTab) {
                        0 -> ProfileAndRegisterTabContent(
                            userProgress = userProgress,
                            showCreateNewForm = showCreateNewForm,
                            usernameInput = usernameInput,
                            onUsernameChange = { usernameInput = it },
                            emailInput = emailInput,
                            onEmailChange = { emailInput = it },
                            pinInput = pinInput,
                            onPinChange = { pinInput = it },
                            selectedAvatar = selectedAvatar,
                            onAvatarSelected = { avatar ->
                                selectedAvatar = avatar
                                if (!showCreateNewForm) {
                                    onSelectAvatar(avatar)
                                }
                            },
                            migrateGuest = migrateGuest,
                            onMigrateGuestChange = { migrateGuest = it },
                            onSubmitRegistration = {
                                val res = onRegisterUser(
                                    usernameInput,
                                    emailInput,
                                    pinInput,
                                    selectedAvatar,
                                    migrateGuest
                                )
                                when (res) {
                                    is AuthResult.Success -> {
                                        isErrorFeedback = false
                                        feedbackMessage = "¡Cuenta '${res.user.username}' registrada! Cada puntuación individual quedará guardada en tu usuario."
                                        usernameInput = ""
                                        emailInput = ""
                                        pinInput = ""
                                        showCreateNewForm = false
                                    }
                                    is AuthResult.Error -> {
                                        isErrorFeedback = true
                                        feedbackMessage = res.message
                                    }
                                }
                            },
                            onToggleCreateForm = { show ->
                                showCreateNewForm = show
                                feedbackMessage = null
                            },
                            onLogout = {
                                onLogout()
                                showCreateNewForm = true
                                isErrorFeedback = false
                                feedbackMessage = "Has cerrado sesión. Ahora juegas en modo invitado."
                            },
                            onSwitchToScoresTab = { selectedTab = 1 }
                        )

                        1 -> IndividualScoresTabContent(
                            userProgress = userProgress,
                            onOpenGameById = { gameId ->
                                onDismiss()
                                onOpenGameById(gameId)
                            },
                            onGoToRegister = {
                                selectedTab = 0
                                showCreateNewForm = true
                            }
                        )

                        2 -> RegisteredUsersRankingTabContent(
                            userProgress = userProgress,
                            pendingLoginUser = pendingLoginUser,
                            loginPinAttempt = loginPinAttempt,
                            onPinAttemptChange = { loginPinAttempt = it },
                            onRequestSwitchUser = { target ->
                                if (target.id == userProgress.activeUser?.id) return@RegisteredUsersRankingTabContent
                                if (target.hasPin) {
                                    pendingLoginUser = target
                                    loginPinAttempt = ""
                                    feedbackMessage = null
                                } else {
                                    when (val res = onLoginUser(target.id, "")) {
                                        is AuthResult.Success -> {
                                            pendingLoginUser = null
                                            isErrorFeedback = false
                                            feedbackMessage = "Sesión cambiada a '${res.user.username}'."
                                        }
                                        is AuthResult.Error -> {
                                            isErrorFeedback = true
                                            feedbackMessage = res.message
                                        }
                                    }
                                }
                            },
                            onConfirmPinLogin = {
                                val target = pendingLoginUser ?: return@RegisteredUsersRankingTabContent
                                when (val res = onLoginUser(target.id, loginPinAttempt)) {
                                    is AuthResult.Success -> {
                                        pendingLoginUser = null
                                        loginPinAttempt = ""
                                        isErrorFeedback = false
                                        feedbackMessage = "¡Bienvenido de nuevo, ${res.user.username}!"
                                    }
                                    is AuthResult.Error -> {
                                        isErrorFeedback = true
                                        feedbackMessage = res.message
                                    }
                                }
                            },
                            onCancelPinLogin = {
                                pendingLoginUser = null
                                loginPinAttempt = ""
                            },
                            onCreateNewAccountClick = {
                                selectedTab = 0
                                showCreateNewForm = true
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun DialogTabChip(
    label: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(10.dp),
        color = if (selected) GoldPrimary else CharcoalSurface,
        border = BorderStroke(
            1.dp,
            if (selected) GoldLight else GoldPrimary.copy(alpha = 0.25f)
        ),
        modifier = modifier.clickable { onClick() }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 9.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (selected) Color(0xFF1B1008) else GoldPrimary,
                modifier = Modifier.size(15.dp)
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = if (selected) Color(0xFF1B1008) else Color(0xFFEAE0D5),
                    fontSize = 11.sp
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
private fun ProfileAndRegisterTabContent(
    userProgress: UserProgressState,
    showCreateNewForm: Boolean,
    usernameInput: String,
    onUsernameChange: (String) -> Unit,
    emailInput: String,
    onEmailChange: (String) -> Unit,
    pinInput: String,
    onPinChange: (String) -> Unit,
    selectedAvatar: PlayerAvatar,
    onAvatarSelected: (PlayerAvatar) -> Unit,
    migrateGuest: Boolean,
    onMigrateGuestChange: (Boolean) -> Unit,
    onSubmitRegistration: () -> Unit,
    onToggleCreateForm: (Boolean) -> Unit,
    onLogout: () -> Unit,
    onSwitchToScoresTab: () -> Unit
) {
    val totalGames = BookDatabase.getAllGames().size

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        if (userProgress.isRegistered && !showCreateNewForm) {
            val active = userProgress.activeUser!!
            item {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = CharcoalSurface),
                    border = BorderStroke(1.dp, GoldPrimary.copy(alpha = 0.5f)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painter = painterResource(id = active.avatar.drawableRes),
                                contentDescription = active.avatar.displayName,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(64.dp)
                                    .clip(CircleShape)
                                    .border(2.5.dp, GoldPrimary, CircleShape)
                            )
                            Spacer(modifier = Modifier.width(14.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = active.username,
                                        style = MaterialTheme.typography.titleLarge.copy(
                                            fontWeight = FontWeight.ExtraBold,
                                            color = Color.White
                                        )
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Icon(
                                        imageVector = Icons.Default.CheckCircle,
                                        contentDescription = "Registrado",
                                        tint = EmeraldWin,
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                                if (active.emailOrTag.isNotBlank()) {
                                    Text(
                                        text = active.emailOrTag,
                                        style = MaterialTheme.typography.bodySmall.copy(
                                            color = Color(0xFFCBBBA8)
                                        )
                                    )
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Personaje activo: ${active.avatar.displayName}",
                                    style = MaterialTheme.typography.labelMedium.copy(
                                        color = GoldLight,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))
                        HorizontalDivider(color = GoldPrimary.copy(alpha = 0.2f))
                        Spacer(modifier = Modifier.height(12.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            MiniProfileStat("Puntuación", "${userProgress.totalXp} pts", GoldLight)
                            MiniProfileStat(
                                "Partida Actual",
                                "#${userProgress.unlockedUpToIndex + 1}/$totalGames",
                                EmeraldWin
                            )
                            MiniProfileStat(
                                "Guardadas",
                                "${userProgress.individualGameScores.size}",
                                AmberStar
                            )
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        Button(
                            onClick = onSwitchToScoresTab,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = GoldPrimary,
                                contentColor = Color(0xFF1B1008)
                            ),
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(Icons.Default.EmojiEvents, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Ver Mis Puntuaciones Individuales", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }

            item {
                Text(
                    text = "Cambiar Personaje de Viaje (Home)",
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = GoldLight
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                AvatarPickerRow(
                    selectedAvatar = active.avatar,
                    onSelect = onAvatarSelected
                )
            }

            item {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedButton(
                        onClick = { onToggleCreateForm(true) },
                        border = BorderStroke(1.dp, GoldPrimary),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(Icons.Default.PersonAdd, contentDescription = null, tint = GoldLight, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Nuevo Usuario", color = GoldLight, fontSize = 12.sp)
                    }

                    OutlinedButton(
                        onClick = onLogout,
                        border = BorderStroke(1.dp, Color(0xFF9E7B66)),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(Icons.AutoMirrored.Filled.Logout, contentDescription = null, tint = Color(0xFFD8C8B4), modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Cerrar Sesión", color = Color(0xFFD8C8B4), fontSize = 12.sp)
                    }
                }
            }
        } else {
            // Formulario de Registro de Usuario
            item {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = CharcoalSurface),
                    border = BorderStroke(1.dp, GoldPrimary.copy(alpha = 0.45f)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Crear Cuenta de Jugador",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.ExtraBold,
                                color = GoldLight
                            )
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Registra tu usuario para conservar cada puntuación individual por partida, tu personaje en Home y tu avance desbloqueando partidas 1 a 1.",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = Color(0xFFD4C4B4),
                                lineHeight = 18.sp
                            )
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        Text(
                            text = "1. Elige tu Personaje para partir desde Home:",
                            style = MaterialTheme.typography.labelLarge.copy(
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        AvatarPickerRow(
                            selectedAvatar = selectedAvatar,
                            onSelect = onAvatarSelected
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        OutlinedTextField(
                            value = usernameInput,
                            onValueChange = onUsernameChange,
                            label = { Text("Nombre de Usuario *") },
                            placeholder = { Text("Ej. MaestroCapablanca") },
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = GoldPrimary,
                                unfocusedBorderColor = GoldPrimary.copy(alpha = 0.35f),
                                focusedLabelColor = GoldLight
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("register_username_input")
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        OutlinedTextField(
                            value = emailInput,
                            onValueChange = onEmailChange,
                            label = { Text("Correo o Club de Ajedrez (Opcional)") },
                            placeholder = { Text("jugador@club.com") },
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = GoldPrimary,
                                unfocusedBorderColor = GoldPrimary.copy(alpha = 0.35f),
                                focusedLabelColor = GoldLight
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        OutlinedTextField(
                            value = pinInput,
                            onValueChange = onPinChange,
                            label = { Text("PIN o Contraseña Local (Opcional)") },
                            placeholder = { Text("Deja vacío para acceso directo") },
                            singleLine = true,
                            visualTransformation = PasswordVisualTransformation(),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = GoldPrimary,
                                unfocusedBorderColor = GoldPrimary.copy(alpha = 0.35f),
                                focusedLabelColor = GoldLight
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )

                        if (!userProgress.isRegistered &&
                            (userProgress.totalXp > 0 || userProgress.individualGameScores.isNotEmpty())
                        ) {
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { onMigrateGuestChange(!migrateGuest) }
                            ) {
                                Checkbox(
                                    checked = migrateGuest,
                                    onCheckedChange = onMigrateGuestChange,
                                    colors = CheckboxDefaults.colors(checkedColor = GoldPrimary)
                                )
                                Text(
                                    text = "Guardar mis ${userProgress.totalXp} pts actuales de invitado en este usuario",
                                    style = MaterialTheme.typography.bodySmall.copy(color = Color(0xFFEAE0D5))
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        Button(
                            onClick = onSubmitRegistration,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = GoldPrimary,
                                contentColor = Color(0xFF1B1008)
                            ),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("register_submit_button")
                        ) {
                            Icon(Icons.Default.PersonAdd, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Registrar Usuario y Guardar Puntos", fontWeight = FontWeight.ExtraBold)
                        }

                        if (userProgress.isRegistered) {
                            Spacer(modifier = Modifier.height(8.dp))
                            OutlinedButton(
                                onClick = { onToggleCreateForm(false) },
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text("Volver a mi perfil actual", color = Color(0xFFD8C8B4))
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun AvatarPickerRow(
    selectedAvatar: PlayerAvatar,
    onSelect: (PlayerAvatar) -> Unit
) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        items(PlayerAvatar.entries) { avatar ->
            val isSelected = avatar == selectedAvatar
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = if (isSelected) GoldPrimary.copy(alpha = 0.22f) else Color(0xFF1A110C),
                border = BorderStroke(
                    width = if (isSelected) 2.dp else 1.dp,
                    color = if (isSelected) GoldLight else GoldPrimary.copy(alpha = 0.25f)
                ),
                modifier = Modifier
                    .width(96.dp)
                    .clickable { onSelect(avatar) }
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(8.dp)
                ) {
                    Image(
                        painter = painterResource(id = avatar.drawableRes),
                        contentDescription = avatar.displayName,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .border(
                                width = if (isSelected) 2.dp else 1.dp,
                                color = if (isSelected) GoldLight else Color.Gray,
                                shape = CircleShape
                            )
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = avatar.displayName,
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = if (isSelected) GoldLight else Color.White,
                            fontSize = 10.sp
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
private fun IndividualScoresTabContent(
    userProgress: UserProgressState,
    onOpenGameById: (String) -> Unit,
    onGoToRegister: () -> Unit
) {
    val scoresList = remember(userProgress.individualGameScores) {
        userProgress.individualGameScores.values.sortedBy { it.gameNumber }
    }
    val sumIndividualPoints = remember(scoresList) {
        scoresList.sumOf { it.totalScore }
    }
    val dateFormat = remember { SimpleDateFormat("dd/MM HH:mm", Locale.getDefault()) }

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        if (!userProgress.isRegistered) {
            item {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = GoldPrimary.copy(alpha = 0.14f),
                    border = BorderStroke(1.dp, GoldPrimary.copy(alpha = 0.5f)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(12.dp)
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Modo Invitado Activo",
                                style = MaterialTheme.typography.labelLarge.copy(
                                    color = GoldLight,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            Text(
                                text = "Regístrate gratis para asociar estas puntuaciones individuales a tu nombre de usuario.",
                                style = MaterialTheme.typography.bodySmall.copy(color = Color(0xFFD8C8B4))
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(
                            onClick = onGoToRegister,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = GoldPrimary,
                                contentColor = Color(0xFF1B1008)
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text("Registrar", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }

        item {
            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = CharcoalSurface),
                border = BorderStroke(1.dp, GoldPrimary.copy(alpha = 0.35f)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    MiniProfileStat("Usuario", userProgress.displayUsername, Color.White)
                    MiniProfileStat("Pts en Partidas", "$sumIndividualPoints pts", GoldLight)
                    MiniProfileStat("Registradas", "${scoresList.size}", EmeraldWin)
                }
            }
        }

        if (scoresList.isEmpty()) {
            item {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 36.dp)
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = Icons.Default.EmojiEvents,
                            contentDescription = null,
                            tint = GoldPrimary.copy(alpha = 0.5f),
                            modifier = Modifier.size(48.dp)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Aún no tienes puntuaciones individuales guardadas.",
                            style = MaterialTheme.typography.titleSmall.copy(color = Color.White),
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Parte desde HOME hacia la Partida #1 para registrar tu primera puntuación.",
                            style = MaterialTheme.typography.bodySmall.copy(color = Color(0xFFB8A898)),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        } else {
            items(scoresList, key = { it.gameId }) { record ->
                IndividualScoreRowCard(
                    record = record,
                    formattedDate = dateFormat.format(Date(record.updatedAt)),
                    onClick = { onOpenGameById(record.gameId) }
                )
            }
        }
    }
}

@Composable
private fun IndividualScoreRowCard(
    record: GameScoreRecord,
    formattedDate: String,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = CharcoalSurface),
        border = BorderStroke(
            1.dp,
            if (record.isCompleted) EmeraldWin.copy(alpha = 0.45f) else GoldPrimary.copy(alpha = 0.3f)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    Surface(
                        shape = CircleShape,
                        color = if (record.isCompleted) EmeraldWin.copy(alpha = 0.2f) else GoldPrimary.copy(alpha = 0.2f),
                        border = BorderStroke(1.dp, if (record.isCompleted) EmeraldWin else GoldPrimary),
                        modifier = Modifier.size(34.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(
                                text = "#${record.gameNumber}",
                                style = MaterialTheme.typography.labelMedium.copy(
                                    fontWeight = FontWeight.ExtraBold,
                                    color = if (record.isCompleted) EmeraldWin else GoldLight
                                )
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = record.gameTitle,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            ),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = "Guardado: $formattedDate • ${if (record.isCompleted) "Superada ✓" else "En estudio"}",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = Color(0xFFB8A898)
                            )
                        )
                    }
                }

                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "${record.totalScore} pts",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.ExtraBold,
                            color = GoldLight
                        )
                    )
                    Row {
                        repeat(3) { i ->
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = null,
                                tint = if (i < record.stars) AmberStar else Color.Gray.copy(alpha = 0.3f),
                                modifier = Modifier.size(13.dp)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                ScorePill(label = "Estudio: ${record.studyScore}/100 pts", color = GoldPrimary)
                ScorePill(label = "Momento Crítico: ${record.challengeScore} pts", color = EmeraldWin)
            }
        }
    }
}

@Composable
private fun ScorePill(label: String, color: Color) {
    Surface(
        shape = RoundedCornerShape(6.dp),
        color = color.copy(alpha = 0.14f),
        border = BorderStroke(0.5.dp, color.copy(alpha = 0.45f))
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall.copy(
                color = Color(0xFFEAE0D5),
                fontWeight = FontWeight.Medium,
                fontSize = 10.sp
            ),
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
        )
    }
}

@Composable
private fun RegisteredUsersRankingTabContent(
    userProgress: UserProgressState,
    pendingLoginUser: RegisteredUser?,
    loginPinAttempt: String,
    onPinAttemptChange: (String) -> Unit,
    onRequestSwitchUser: (RegisteredUser) -> Unit,
    onConfirmPinLogin: () -> Unit,
    onCancelPinLogin: () -> Unit,
    onCreateNewAccountClick: () -> Unit
) {
    val sortedUsers = remember(userProgress.registeredUsers) {
        userProgress.registeredUsers.sortedByDescending { it.totalXp }
    }

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        if (pendingLoginUser != null) {
            item {
                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF2B1B12)),
                    border = BorderStroke(1.dp, GoldLight),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(
                            text = "Introduce el PIN de '${pendingLoginUser.username}'",
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = GoldLight
                            )
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = loginPinAttempt,
                            onValueChange = onPinAttemptChange,
                            label = { Text("Contraseña / PIN") },
                            singleLine = true,
                            visualTransformation = PasswordVisualTransformation(),
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            OutlinedButton(
                                onClick = onCancelPinLogin,
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("Cancelar")
                            }
                            Button(
                                onClick = onConfirmPinLogin,
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = GoldPrimary,
                                    contentColor = Color(0xFF1B1008)
                                ),
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("Entrar", fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
        }

        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Clasificación de Usuarios Registrados",
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = GoldLight
                    )
                )
                OutlinedButton(
                    onClick = onCreateNewAccountClick,
                    border = BorderStroke(1.dp, GoldPrimary),
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Icon(Icons.Default.PersonAdd, contentDescription = null, tint = GoldLight, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Añadir", color = GoldLight, fontSize = 11.sp)
                }
            }
        }

        if (sortedUsers.isEmpty()) {
            item {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 36.dp)
                ) {
                    Text(
                        text = "Todavía no hay perfiles registrados en este dispositivo.\nPulsa 'Añadir' para crear tu usuario.",
                        style = MaterialTheme.typography.bodySmall.copy(color = Color(0xFFB8A898)),
                        textAlign = TextAlign.Center
                    )
                }
            }
        } else {
            itemsIndexed(sortedUsers, key = { _, u -> u.id }) { idx, user ->
                val isCurrent = user.id == userProgress.activeUser?.id
                val medal = when (idx) {
                    0 -> "🥇"
                    1 -> "🥈"
                    2 -> "🥉"
                    else -> "#${idx + 1}"
                }

                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isCurrent) Color(0xFF2E1E13) else CharcoalSurface
                    ),
                    border = BorderStroke(
                        width = if (isCurrent) 1.5.dp else 1.dp,
                        color = if (isCurrent) GoldLight else GoldPrimary.copy(alpha = 0.25f)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onRequestSwitchUser(user) }
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(12.dp)
                    ) {
                        Text(text = medal, fontSize = 18.sp)
                        Spacer(modifier = Modifier.width(10.dp))
                        Image(
                            painter = painterResource(id = user.avatar.drawableRes),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(42.dp)
                                .clip(CircleShape)
                                .border(1.5.dp, GoldPrimary, CircleShape)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = user.username,
                                    style = MaterialTheme.typography.bodyLarge.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                )
                                if (user.hasPin) {
                                    Spacer(modifier = Modifier.width(5.dp))
                                    Icon(
                                        imageVector = Icons.Default.Lock,
                                        contentDescription = "Protegido con PIN",
                                        tint = GoldPrimary,
                                        modifier = Modifier.size(13.dp)
                                    )
                                }
                            }
                            Text(
                                text = "Partida #${user.unlockedUpToIndex + 1} • ${user.completedGamesCount} superadas",
                                style = MaterialTheme.typography.labelSmall.copy(color = Color(0xFFC4B4A4))
                            )
                        }
                        Column(horizontalAlignment = Alignment.End) {
                            Text(
                                text = "${user.totalXp} pts",
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontWeight = FontWeight.ExtraBold,
                                    color = GoldLight
                                )
                            )
                            Text(
                                text = if (isCurrent) "ACTIVO" else "Cambiar",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = if (isCurrent) EmeraldWin else GoldPrimary,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun MiniProfileStat(label: String, value: String, valueColor: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.ExtraBold,
                color = valueColor
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall.copy(
                color = Color(0xFFB8A898)
            )
        )
    }
}
