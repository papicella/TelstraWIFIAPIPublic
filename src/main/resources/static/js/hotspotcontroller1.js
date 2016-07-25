var hotspotsfinderapp = angular.module('hotspotsfinderapp',['ngRoute'])

hotspotsfinderapp.controller('hotspotController',
            ['$scope','$http', '$routeParams', '$timeout', function($scope, $http, $routeParams, $timeout) {
  $scope.showDetail = true;
  $scope.showDetailError = true;
  $scope.radius = 10000;
  $scope.lat = 0;
  $scope.lon = 0;
  $scope.hotspots = [];
  $scope.search_url = '/hotspots/search'
  $scope.markers = [];
  $scope.map = null;



  $scope.search = function() {
        $scope.hotspots = [];

        //alert("Search called now Lat = " + $scope.lat + " Lon " + $scope.lon + " Radius = " + $scope.radius)
        if($scope.radius == 0) {
            $scope.radius = 100;
        }

        function clearMarkers() {
             for (var i = 0; i < $scope.markers.length; i++) {
                  $scope.markers[i].setMap(null);
             }
        }

        req = {}
        req.lat = $scope.lat;
        req.lon = $scope.lon;
        req.radius = $scope.radius;

        if($scope.markers.length > 1) {
            clearMarkers();
            $scope.markers = [];
        }

        $http.post($scope.search_url, req).then(function(response) {
            if(response.status == 200) {
                console.log("Response " + JSON.stringify(response.data));
                //alert("Received :" + JSON.stringify(response.data))
                $scope.hotspots = response.data;
                if($scope.hotspots.length > 0) {
                        $scope.hotspots.map(function(hotspot) {
                            var latlong = {}
                            latlong['lat'] = parseFloat(hotspot.lat);
                            latlong['lng'] = parseFloat(hotspot.long);

                            var marker = new google.maps.Marker({
                                                                position: latlong,
                                                                 map: $scope.map,
                                                                 draggable: false,
                                                                 animation: google.maps.Animation.DROP,
                                                                 title : hotspot.address + "," + hotspot.city + "," + hotspot.state,
                                                                 icon: 'http://maps.google.com/mapfiles/ms/icons/green-dot.png'
                                                                });
                            $scope.markers.push(marker);
                        });
                }
            }
        });
     }

  $scope.init = function() {
          var marker = false;

          function initMap() {
              $scope.map  = new google.maps.Map(document.getElementById('map'), {
                  center: {lat: -34.397, lng: 150.644},
                  zoom: 14
              });
              var infoWindow = new google.maps.InfoWindow({map: $scope.map});

              // Try HTML5 geolocation.
              if (navigator.geolocation) {
                  navigator.geolocation.getCurrentPosition(function(position) {
                      var pos = {
                          lat: position.coords.latitude,
                          lng: position.coords.longitude
                      };

                      //infoWindow.setPosition(pos);
                      //infoWindow.setContent('Location found.');
                      $scope.map.setCenter(pos);
                      //Add lat and lng values to a field that we can save.
//                      document.getElementById('lat').value = pos.lat; //latitude
//                      document.getElementById('lon').value = pos.lng; //longitude

                      $scope.lat = pos.lat; //latitude
                      $scope.lon = pos.lng;//longitude
                      markerLocation(pos);

                  }, function() {
                      handleLocationError(true, infoWindow, $scope.map.getCenter());
                  });
              } else {
                  // Browser doesn't support Geolocation
                  handleLocationError(false, infoWindow, $scope.map.getCenter());
              }

              //Listen for any clicks on the map.
              google.maps.event.addListener($scope.map, 'click', function(event) {
                  //Get the location that the user clicked.
                  var clickedLocation = event.latLng;
                  //If the marker hasn't been added.
                  if($scope.marker === false){
                      //Create the marker.
                      $scope.marker = new google.maps.Marker({
                          position: clickedLocation,
                          map: $scope.map,
                          draggable: true //make it draggable
                      });
                      //Listen for drag events!
                      google.maps.event.addListener($scope.marker, 'dragend', function(event){
                          var clickedLocation = event.latLng;
                          $scope.$apply(function () {
                              $scope.lat = clickedLocation.lat(); //latitude
                              $scope.lon = clickedLocation.lng(); //longitude
                              $scope.map.panTo(clickedLocation);
                              $scope.search();
                          });
                      });

                  } else{
                      //Marker has already been added, so just change its location.
                      $scope.marker.setPosition(clickedLocation);
                      $scope.map.setCenter(clickedLocation);
                      $scope.$apply(function () {
                            $scope.lat = clickedLocation.lat(); //latitude
                            $scope.lon = clickedLocation.lng(); //longitude
                            $scope.search();
                      });
                  }
              });
          }

          //This function will get the marker's current location and then add the lat/long
          //values to our textfields so that we can save the location.
          function markerLocation(location){

               $scope.marker = new google.maps.Marker({
                                         position: location,
                                         map: $scope.map,
                                         draggable: true //make it draggable
                                     });

               google.maps.event.addListener($scope.marker, 'dragend', function(event){
                    var clickedLocation = event.latLng;
                    $scope.$apply(function () {
                        $scope.map.panTo(clickedLocation);
                        $scope.lat = clickedLocation.lat(); //latitude
                        $scope.lon = clickedLocation.lng(); //longitude
                        $scope.search();
                    });
               });

               $scope.$apply(function () {
                    $scope.lat = location.lat; //latitude
                    $scope.lon = location.lng; //longitude
                    $scope.search();
               });
          }

          function handleLocationError(browserHasGeolocation, infoWindow, pos) {
              infoWindow.setPosition(pos);
              infoWindow.setContent(browserHasGeolocation ?
                      'Error: The Geolocation service failed.' :
                      'Error: Your browser doesn\'t support geolocation.');
          }

          google.maps.event.addDomListener(window, 'load', initMap);
   }

   $scope.init();

  }]);


