var Map = {
       __markers : [],
       __restaurants : [],
       __addresses : [],
       __map : null,


      initialize : function() {
        var portland = { lat : 45.5404527, lng : -122.673812 };

        var map = new google.maps.Map(document.getElementById('map'), {
          zoom: 7,
          center: portland,
          mapTypeId: google.maps.MapTypeId.ROADMAP
        })
      },

      getSelectedCategoryAddresses : function() {
        Map.__restaurants = document.getElementByClassName('restaurant-address');
        for ( var i = 0; i < Map.__restaurants.length; i++ ) {
          Map.__addresses.push(Map.__restaurants[i].getAttribute('data-address'));
        }
        return Map.__addresses;
      },


      createMarkers : function (addresses) {
        var geocoder = new google.maps.Geocoder();
        for (var i = 0; i < addresses.length; i++) {
          geocoder.geocode( { 'address' : addresses[i] }, function(results, status) {
            if(status === google.maps.GeocoderStatus.OK) {
              var marker = new google.maps.Marker({
                map : Map.__map,
                Title : '' + addresses[i],
                position : results[0].geometry.location
              });

              Map.__markers.push(marker);
              console.log(marker);
            } else {
              alert('Geocode was not successful for the following reason: ' + status);
            }
          })
          return Map.__markers;
        }
      },


      setMapOnRestaurantMarkers : function(map) {
        for (var i = 0; i < Map.__markers.length; i++) {
          Map.__markers[i].setMap(map);
        }
      },

      deleteMarkers : function() {
        Map.setMapOnRestaurantMarkers(null);
        Map.__markers = [];
        Map.__addresses = [];
        Map.__restaurants = [];
      }

    };//end of Map
