module.exports = function(grunt) {
	// load all grunt tasks matching the ['grunt-*', '@*/grunt-*'] patterns
	require('load-grunt-tasks')(grunt);

	// Project configuration.
	grunt.initConfig({
			pkg: grunt.file.readJSON('package.json'),

			copy: {
				devLib: {
					expand: true,
					filter: 'isFile',
					flatten: true,
					cwd: './bower_components/',
					src: ['angular/angular.js','livereload-js/dist/livereload.js'],
					dest: 'build/js/'
				},
				devSrc: {
					expand: true,
					cwd: 'src/js/',
					src: '**',
					dest: 'build/js/'
				},
				toServer: {
					expand: true,
					cwd: 'build/',
					src: '**',
					dest: '../server/web/'
				}
			},

			jade: {
				dev: {
					options: {
						pretty: true,
						data: {
							debug: true
						}
					},
					files: {
						//"build/decoy.html": 'src/decoy.jade',
						"build/index.html": 'src/index.jade',
					}
				},
				prod: {
					options: {
						pretty: false,
						data: {
							debug: false
						}
					},
					files: {
						//"build/index.html": 'src/index.jade',//decoy
						"build/index.html": 'src/index.jade',//real app
					}
				}
			},

			stylus: {
				dev: {
					options: {
						compress: false,
						'include css': true
					},
					files: {
						'build/main.css': 'src/css/main.styl',
						//'build/decoy.css': 'src/css/decoy.styl',
					//'path/to/another.css': ['path/to/sources/*.styl', 'path/to/more/*.styl'] // compile and concat into single file
					}
				}
			},

			watch: {
				views: {
					files: ['src/**/*.jade','src/**/*.svg'],
					tasks: ['jade:dev'],
					options: {
						livereload: true
					}
				},
				css: {
					files: 'src/css/*.styl',
					tasks: ['stylus:dev'],
					options: {
						livereload: true
					}
				},
				js: {
					files: 'src/js/**/*.js',
					tasks: ['copy:devSrc'],
					options: {
						livereload: true
					}
				}
			}
	});


	// Default task(s).
	grunt.registerTask(
		'dev', [
			'jade:dev',
			'copy',
			'watch'
		]
	);
	grunt.registerTask('default', 'dev');
};
