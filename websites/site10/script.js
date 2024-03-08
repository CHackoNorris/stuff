function playMusic() {
    var audio = new Audio('music.mp3');
    audio.play();
    return "done.";
}

window.addEventListener('DOMContentLoaded', (event) => {
    console.log('Page loaded');
    console.log('Type playMusic() in the console to play the audio.');
});
