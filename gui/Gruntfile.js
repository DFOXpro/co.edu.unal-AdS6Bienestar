module.exports = function (grunt) {
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
				src: [
					'angular/angular.min.js',
					'livereload-js/dist/livereload.js',
					'cryptico/cryptico.min.js'
				],
				dest: 'build/js/lib/'
			},
			devSrc: {
				expand: true,
				cwd: 'src/js/',
				src: '**',
				dest: 'build/js/'
			},
			devThemes: {
				expand: true,
				cwd: 'src/css/',
				src: '**',
				dest: 'build/css/'
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
					"build/paneles/panel_admin.html": 'src/theme/Vistas_JADE/admin_main.jade',
					"build/login_general.html": 'src/theme/Vistas_JADE/login_general.jade',
					"build/registro_general.html": 'src/theme/Vistas_JADE/registro_general.jade',

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
				files: ['src/**/*.jade', 'src/**/*.svg'],
				tasks: ['jade:dev'],
				options: {
					livereload: true
				}
			},
			css: {
				files: [
					'src/css/*.styl',
					'src/css/*.css'
				],
				tasks: [
					//'stylus:dev'
					'copy:devThemes'
				],
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
		'client', [
			'jade:dev',
			'copy:devLib',
			'copy:devSrc',
			'copy:devThemes',
			'watch'
		]
	);
	grunt.registerTask(
		'toServer', [
			'jade:dev',
			'copy'
		]
	);
	grunt.registerTask('default', 'toServer');
};
