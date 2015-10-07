document.addEventListener('DOMContentLoaded', function () {
    var circles = document.querySelectorAll('.circle'),
        degreeIncrement = 360 / circles.length,
        degrees = -degreeIncrement;

    for (var i = circles.length; i--;) {
      circles[i].style.webkitTransform = 'rotate(' + (degrees += degreeIncrement) + 'deg) translate(250px) rotate(-' + degrees + 'deg)'

      console.log(degrees)
    }
  }, true);
