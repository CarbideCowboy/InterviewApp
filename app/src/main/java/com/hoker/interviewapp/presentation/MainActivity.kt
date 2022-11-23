package com.hoker.interviewapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.hoker.interviewapp.domain.models.Character
import com.hoker.interviewapp.domain.models.Location
import com.hoker.interviewapp.ui.theme.InterviewAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InterviewAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //location dialog
                    if(viewModel.showLocationDialog.value) {
                        viewModel.selectedCharacter.value.let {
                            CharacterLocationDialog(it!!) {
                                viewModel.showLocationDialog.value = false
                            }
                        }
                    }

                    //loading circle
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        if(viewModel.characterList.value == null) {
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }

                    //character list
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        if(viewModel.characterList.value != null) {
                            items(viewModel.characterList.value!!) {character ->
                                CharacterCard(character)
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun CharacterLocationDialog(character: Character, onDismissAction: () -> Unit) {
        AlertDialog(
            onDismissRequest = { onDismissAction.invoke() },
            title = {
                Text(
                    text = "Location Information",
                    fontSize = 24.sp
                )
            },
            text = {
                Text(
                    text = viewModel.getLocationText(character),
                    fontSize = 16.sp
                )
            },
            confirmButton = {
                TextButton(
                    onClick = { onDismissAction.invoke() }
                ) {
                    Text(
                        text = "Close"
                    )
                }
            }
        )
    }

    @Composable
    fun CharacterCard(character: Character) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clickable {
                    viewModel.selectedCharacter.value = character
                    viewModel.showLocationDialog.value = true
                }
        ) {
            Row(
                modifier = Modifier.padding(4.dp)) {
                Image(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    painter = rememberAsyncImagePainter(character.imageUrl),
                    contentDescription = null
                )
                Column(
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Text(
                        text = character.name
                    )
                    Text(
                        text = character.status
                    )
                    Text(
                        text = character.species
                    )
                }
            }
        }
    }
}