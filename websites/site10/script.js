// script.js

// Define a custom command to play the audio file
function playMusic() {
    var audio = new Audio('music.mp3');
    audio.play();
}

// Register the custom command in the console
window.addEventListener('DOMContentLoaded', (event) => {
    console.log('Page loaded');
    console.log('Type playMusic() in the console to play the audio.');
});
