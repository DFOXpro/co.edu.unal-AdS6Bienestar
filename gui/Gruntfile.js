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
					src: ['Angular/angular.js'],
					dest: 'build/js/'
				},
				devSrc: {
					expand: true,
					cwd: 'src/js/',
					src: '**',
					dest: 'build/js/'
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
						"build/app.html": 'src/views/main.jade',
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
						"build/~index.html": 'src/views/main.jade',//real app
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
					files: ['src/views/**/*.jade','src/views/**/*.svg'],
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
			'copy',
			'stylus:dev',
			'jade:dev',
			'watch'
		]
	);
	grunt.registerTask('default', 'dev');
};
