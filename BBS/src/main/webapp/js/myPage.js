function tag () {
  let progress = document.querySelector('.progressTag')
  let interval = 0
  let updatesPerSecond = 1000 / 60
  let end = progress.max * 0.8

  function animator () {
    progress.value = progress.value + interval
    if ( progress.value + interval < end){
      setTimeout(animator, updatesPerSecond);
    } else { 
      progress.value = end
    }
  }

  setTimeout(() => {
    animator()
  }, updatesPerSecond)
}

tag()/**
 * 
 */