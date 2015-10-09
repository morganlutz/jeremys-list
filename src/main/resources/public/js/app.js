var Circles = {
        __wrapperID: 'circle-wrapper',
        __startAngle: 141,

        __classes: {
          circle: 'category',
          linedUp: 'circle-lined-up',
          oneCircle: 'circle-one-circle'
        },

        get wrapper() {
          return document.getElementById(Circles.__wrapperID);
        },

        get circles() {
          return document.querySelectorAll('.' + Circles.__classes.circle);
        },

        formCircle: function () {
          var circles = Circles.circles,
              translateAmount = (Circles.wrapper.offsetWidth / 2) - 1,
              degreeIncrement = 360 / circles.length,
              degrees = parseInt(Circles.__startAngle, 10);

          for (var i = 0; i < circles.length; i++) {
            circles[i].classList.remove(Circles.__classes.linedUp);
            circles[i].classList.remove(Circles.__classes.oneCircle);

            circles[i].style.setProperty('left', '50%');
            circles[i].style.setProperty('-webkit-transform', 'scale(1) rotate(' + (degrees += degreeIncrement) + 'deg) translate(' + translateAmount + 'px) rotate(-' + degrees + 'deg)');
          }
        },

        formLine: function () {
          var circles = Circles.circles,
              lineWidth = -Circles.wrapper.offsetWidth,
              centerPoint = Circles.wrapper.offsetWidth / 2,
              leftIncrement = false,
              left = false;

          for (var i = 0; i < circles.length; i++) {
            if (!leftIncrement) {
              leftIncrement = circles[i].offsetWidth + (circles[i].offsetWidth / 8);
              left = ((centerPoint - (leftIncrement / 2) * (circles.length))) - leftIncrement + 10;
            }

            circles[i].classList.add(Circles.__classes.linedUp);

            if (circles.length === 1)
              circles[i].classList.add(Circles.__classes.oneCircle);

            circles[i].style.setProperty('-webkit-transform', 'rotate(0)')
            circles[i].style.setProperty('left', (left += leftIncrement) + 'px')
          }
        },

        create: function () {
          Circles.destroy();

          var circle;

          var countInput = document.getElementById('circle-count');

          for (var i = 0; i < parseInt(countInput.value); i++) {
            circle = document.createElement('div');

            circle.className = Circles.__classes.circle;
            circle.innerHTML = i + 1;

            Circles.wrapper.appendChild(circle);
          }

          setTimeout(Circles.formCircle, 100);
        },

        destroy: function () {
          var circles = Circles.circles;

          for (var i = circles.length; i--;)
            circles[i].parentNode.removeChild(circles[i]);
        },

        setActive: function () {
          var circles = Circles.circles;
          circles = $('.category').removeClass('circle-active');
          var splitPath = window.location.pathname.split('/');
          var circle = document.getElementById(splitPath[1]);
            if (circle){
              circle.classList.add('circle-active');
            }
          }
       };

      $(function () {
        $('#circle-count').change(Circles.create);

        $(document)
          .on('click touchend', '.category', function () {
            var isActive = this.classList.contains('circle-active'),
                circles = $('.category').removeClass('circle-active');

            circles.removeClass('circle-active');

            if (isActive) {
              // Circles.formCircle();
              //show all restaurants in that category

              document.body.offsetWidth;
            }	else {
              this.classList.add('circle-active');
              Circles.formLine();


            }
          });

        // createCircles();
        initializeCircles();
      });

      $(document).on('click', 'a.category', function() {
        var category = this.getAttribute('data-category');
        history.pushState({}, category, '/' + category);
      });

      window.addEventListener('popstate', function (event) {
        $(Circles.setActive());
      }, true);



      function initializeCircles() {
        if(window.location.pathname === "/") {
          Circles.formCircle();
        } else if (window.location.pathname === "/coffee") {
          $("#coffee").addClass("circle-active");
          Circles.formLine();
        }
      }
