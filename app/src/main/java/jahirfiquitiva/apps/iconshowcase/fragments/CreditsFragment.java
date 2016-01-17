package jahirfiquitiva.apps.iconshowcase.fragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;

import jahirfiquitiva.apps.iconshowcase.R;
import jahirfiquitiva.apps.iconshowcase.utilities.ThemeUtils;
import jahirfiquitiva.apps.iconshowcase.utilities.Utils;

public class CreditsFragment extends Fragment {

    private boolean WITH_DEVELOPMENT_CREDITS = true, WITH_ALTERNATIVE_LAYOUT = true;

    private Context context;
    private ViewGroup layout;
    Drawable person, facebook, gplus, twitter, website, youtube, community, playstore, github,
            bugs, donate, collaboratorsIcon, libs, uiCollaboratorsIcon, sherryIcon, email;
    ImageView iconAuthor, iconDev, iconAuthorFacebook, iconAuthorGPlus, iconAuthorCommunity,
            youtubeIcon, twitterIcon, playStoreIcon, iconAuthorWebsite, uiCollaboratorsIV,
            iconDevGitHub, iconDevCommunity, donateIcon, bugIcon, collaboratorsIV, libsIcon,
            sherryIV, emailIV;
    LinearLayout jahirL, authorFB, authorGPlus, authorTwitter, authorWebsite, authorYouTube,
            authorCommunity, authorPlayStore, devGitHub, libraries, uiCollaborators,
            thanksSherry, contributorsLayout, donateL, bugsL, communityL, emailL;
    boolean withLinkToFacebook, withLinkToTwitter, withLinkToGPlus, withLinkToYouTube,
            withLinkToCommunity, withLinkToPlayStore, withLinkToWebsite;
    String[] libsLinks, contributorsLinks, uiCollaboratorsLinks;

    private void setupBooleans() {
        withLinkToFacebook = true;
        withLinkToTwitter = true;
        withLinkToGPlus = true;
        withLinkToYouTube = true;
        withLinkToCommunity = true;
        withLinkToPlayStore = true;
        withLinkToWebsite = true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        context = getActivity();

        setupBooleans();

        libsLinks = context.getResources().getStringArray(R.array.libs_links);
        contributorsLinks = context.getResources().getStringArray(R.array.contributors_links);
        uiCollaboratorsLinks = context.getResources().getStringArray(R.array.ui_collaborators_links);

        if (layout != null) {
            ViewGroup parent = (ViewGroup) layout.getParent();
            if (parent != null) {
                parent.removeView(layout);
            }
        }

        /*
            *  Uncomment the section you want to use
         */
        try {
            //layout = (ViewGroup) inflater.inflate(R.layout.credits_section, container, false);
            layout = (ViewGroup) inflater.inflate(R.layout.credits_section_alt, container, false);
        } catch (InflateException e) {
            Utils.showLog(e.getLocalizedMessage());
        }

        setupViewsIDs(layout);

        if (!WITH_ALTERNATIVE_LAYOUT) {
            setupLayout(getActivity());
            setupExtraAuthorOptions();
        }

        return layout;
    }

    private void setupViewsIDs(final ViewGroup layout) {
        if (!WITH_ALTERNATIVE_LAYOUT) {
            iconAuthor = (ImageView) layout.findViewById(R.id.icon_author);
            iconDev = (ImageView) layout.findViewById(R.id.icon_dev);
            iconAuthorFacebook = (ImageView) layout.findViewById(R.id.icon_facebook_author);
            iconAuthorGPlus = (ImageView) layout.findViewById(R.id.icon_google_plus_author);
            iconAuthorCommunity = (ImageView) layout.findViewById(R.id.icon_community_author);
            youtubeIcon = (ImageView) layout.findViewById(R.id.icon_youtube);
            twitterIcon = (ImageView) layout.findViewById(R.id.icon_twitter);
            playStoreIcon = (ImageView) layout.findViewById(R.id.icon_play_store);
            iconAuthorWebsite = (ImageView) layout.findViewById(R.id.icon_website_author);
            iconDevGitHub = (ImageView) layout.findViewById(R.id.icon_github);
            donateIcon = (ImageView) layout.findViewById(R.id.icon_donate);
            bugIcon = (ImageView) layout.findViewById(R.id.icon_bug_report);
            libsIcon = (ImageView) layout.findViewById(R.id.icon_libs);
            collaboratorsIV = (ImageView) layout.findViewById(R.id.icon_collaborators);
            sherryIV = (ImageView) layout.findViewById(R.id.icon_sherry);
            uiCollaboratorsIV = (ImageView) layout.findViewById(R.id.icon_ui_design);
            iconDevCommunity = (ImageView) layout.findViewById(R.id.icon_google_plus_community);
            emailIV = (ImageView) layout.findViewById(R.id.icon_email);

            if (!WITH_DEVELOPMENT_CREDITS) {
                View credits = layout.findViewById(R.id.dev_credits);
                credits.setVisibility(View.GONE);
            }

            emailL = (LinearLayout) layout.findViewById(R.id.send_email);
            emailL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Utils.sendEmailWithDeviceInfo(context);
                }
            });

            jahirL = (LinearLayout) layout.findViewById(R.id.devName);
            jahirL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Utils.openLinkInChromeCustomTab(context,
                            Utils.getStringFromResources(context, R.string.dashboard_author_website));
                }
            });

            authorFB = (LinearLayout) layout.findViewById(R.id.author_facebook);
            authorFB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.isAppInstalled(context, "com.facebook.katana")) {
                        Utils.openLink(context,
                                getResources().getString(R.string.iconpack_author_fb));
                    } else {
                        Utils.openLinkInChromeCustomTab(context,
                                getResources().getString(R.string.iconpack_author_fb_alt));
                    }
                }
            });

            authorGPlus = (LinearLayout) layout.findViewById(R.id.add_to_google_plus_circles);
            authorGPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Utils.openLinkInChromeCustomTab(context,
                            getResources().getString(R.string.iconpack_author_gplus));
                }
            });

            authorTwitter = (LinearLayout) layout.findViewById(R.id.twitter);
            authorTwitter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Utils.openLink(context,
                                getResources().getString(R.string.iconpack_author_twitter));
                    } catch (Exception e) {
                        Utils.openLink(context,
                                getResources().getString(R.string.iconpack_author_twitter_alt));
                    }
                }
            });

            authorWebsite = (LinearLayout) layout.findViewById(R.id.visit_website);
            authorWebsite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Utils.openLinkInChromeCustomTab(context,
                            getResources().getString(R.string.iconpack_author_website));
                }
            });

            authorYouTube = (LinearLayout) layout.findViewById(R.id.youtube);
            authorYouTube.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Utils.openLinkInChromeCustomTab(context,
                            getResources().getString(R.string.iconpack_author_youtube));
                }
            });

            authorCommunity = (LinearLayout) layout.findViewById(R.id.community);
            authorCommunity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Utils.openLinkInChromeCustomTab(context,
                            getResources().getString(R.string.iconpack_author_gplus_community));
                }
            });

            authorPlayStore = (LinearLayout) layout.findViewById(R.id.play_store);
            authorPlayStore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Utils.openLinkInChromeCustomTab(context,
                            getResources().getString(R.string.iconpack_author_playstore));
                }
            });

            devGitHub = (LinearLayout) layout.findViewById(R.id.dev_github);
            devGitHub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Utils.openLinkInChromeCustomTab(context,
                            getResources().getString(R.string.dashboard_author_github));
                }
            });

            thanksSherry = (LinearLayout) layout.findViewById(R.id.collaboratorsSherry);
            thanksSherry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showSherryDialog();
                }
            });

            uiCollaborators = (LinearLayout) layout.findViewById(R.id.uiDesign);
            uiCollaborators.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showUICollaboratorsDialog();
                }
            });

            libraries = (LinearLayout) layout.findViewById(R.id.libraries);
            libraries.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showLibrariesDialog();
                }
            });

            contributorsLayout = (LinearLayout) layout.findViewById(R.id.collaborators);
            contributorsLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showContributorsDialog();
                }
            });

            //TODO: Add actual donation link
            donateL = (LinearLayout) layout.findViewById(R.id.donate);
            donateL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Utils.showSimpleSnackbar(layout, "Coming soon", 1);
                }
            });

            bugsL = (LinearLayout) layout.findViewById(R.id.report_bugs);
            bugsL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Utils.openLinkInChromeCustomTab(context,
                            getResources().getString(R.string.dashboard_bugs_report));
                }
            });

            communityL = (LinearLayout) layout.findViewById(R.id.join_google_plus_community);
            communityL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Utils.openLinkInChromeCustomTab(context,
                            getResources().getString(R.string.dashboard_author_gplus_community));
                }
            });
        } else {

            if (!WITH_DEVELOPMENT_CREDITS) {
                CardView devCV = (CardView) layout.findViewById(R.id.devCard);
                devCV.setVisibility(View.GONE);
            }

            AppCompatButton emailBtn = (AppCompatButton) layout.findViewById(R.id.send_email_btn);
            emailBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Utils.sendEmailWithDeviceInfo(context);
                }
            });

            AppCompatButton websiteBtn = (AppCompatButton) layout.findViewById(R.id.website_btn);
            websiteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Utils.openLinkInChromeCustomTab(context,
                            getResources().getString(R.string.iconpack_author_website));
                }
            });

            AppCompatButton forkBtn = (AppCompatButton) layout.findViewById(R.id.fork_btn);
            forkBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Utils.openLinkInChromeCustomTab(context,
                            getResources().getString(R.string.dashboard_author_github));
                }
            });

            AppCompatButton devWebsiteBtn = (AppCompatButton) layout.findViewById(R.id.dev_website_btn);
            devWebsiteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Utils.openLinkInChromeCustomTab(context,
                            getResources().getString(R.string.dashboard_author_website));
                }
            });

            final ImageView popUp = (ImageView) layout.findViewById(R.id.designerPopUp);
            popUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenu popUpMenu = new PopupMenu(context, popUp);
                    popUpMenu.getMenuInflater().inflate(R.menu.designer_popup, popUpMenu.getMenu());
                    popUpMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.designerFacebook:
                                    if (Utils.isAppInstalled(context, "com.facebook.katana")) {
                                        Utils.openLink(context,
                                                getResources().getString(R.string.iconpack_author_fb));
                                    } else {
                                        Utils.openLinkInChromeCustomTab(context,
                                                getResources().getString(R.string.iconpack_author_fb_alt));
                                    }
                                    break;
                                case R.id.designerGooglePlus:
                                    Utils.openLinkInChromeCustomTab(context,
                                            getResources().getString(R.string.iconpack_author_gplus));
                                    break;
                                case R.id.designerCommunity:
                                    Utils.openLinkInChromeCustomTab(context,
                                            getResources().getString(R.string.iconpack_author_gplus_community));
                                    break;
                                case R.id.designerYouTube:
                                    Utils.openLinkInChromeCustomTab(context,
                                            getResources().getString(R.string.iconpack_author_youtube));
                                    break;
                                case R.id.designerTwitter:
                                    try {
                                        Utils.openLink(context,
                                                getResources().getString(R.string.iconpack_author_twitter));
                                    } catch (Exception e) {
                                        Utils.openLink(context,
                                                getResources().getString(R.string.iconpack_author_twitter_alt));
                                    }
                                    break;
                                case R.id.designerPlayStore:
                                    Utils.openLinkInChromeCustomTab(context,
                                            getResources().getString(R.string.iconpack_author_playstore));
                                    break;
                            }
                            return false;
                        }
                    });
                    popUpMenu.show();
                }
            });

            final ImageView devPopUp = (ImageView) layout.findViewById(R.id.developerPopUp);
            devPopUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenu devPopUpMenu = new PopupMenu(context, devPopUp);
                    devPopUpMenu.getMenuInflater().inflate(R.menu.dev_popup, devPopUpMenu.getMenu());
                    devPopUpMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.developmentSherry:
                                    showSherryDialog();
                                    break;
                                case R.id.developmentContributors:
                                    showContributorsDialog();
                                    break;
                                case R.id.developmentUIDesign:
                                    showUICollaboratorsDialog();
                                    break;
                                case R.id.developmentLibraries:
                                    showLibrariesDialog();
                                    break;
                                case R.id.developmentBugs:
                                    Utils.openLinkInChromeCustomTab(context,
                                            getResources().getString(R.string.dashboard_bugs_report));
                                    break;
                                case R.id.developmentCommunity:
                                    Utils.openLinkInChromeCustomTab(context,
                                            getResources().getString(R.string.dashboard_author_gplus_community));
                                    break;
                            }
                            return false;
                        }
                    });
                    devPopUpMenu.show();
                }
            });
        }
    }

    private void setupLayout(Context context) {
        if (!WITH_ALTERNATIVE_LAYOUT) {
            final int light = ContextCompat.getColor(context, android.R.color.white);
            final int dark = ContextCompat.getColor(context, R.color.grey);

            person = new IconicsDrawable(context)
                    .icon(GoogleMaterial.Icon.gmd_account)
                    .color(ThemeUtils.darkTheme ? light : dark)
                    .sizeDp(24);

            facebook = new IconicsDrawable(context)
                    .icon(GoogleMaterial.Icon.gmd_facebook)
                    .color(ThemeUtils.darkTheme ? light : dark)
                    .sizeDp(24);

            gplus = new IconicsDrawable(context)
                    .icon(GoogleMaterial.Icon.gmd_google_plus)
                    .color(ThemeUtils.darkTheme ? light : dark)
                    .sizeDp(24);

            community = new IconicsDrawable(context)
                    .icon(GoogleMaterial.Icon.gmd_group_work)
                    .color(ThemeUtils.darkTheme ? light : dark)
                    .sizeDp(24);

            twitter = new IconicsDrawable(context)
                    .icon(GoogleMaterial.Icon.gmd_twitter)
                    .color(ThemeUtils.darkTheme ? light : dark)
                    .sizeDp(24);

            github = new IconicsDrawable(context)
                    .icon(GoogleMaterial.Icon.gmd_github)
                    .color(ThemeUtils.darkTheme ? light : dark)
                    .sizeDp(24);

            youtube = new IconicsDrawable(context)
                    .icon(GoogleMaterial.Icon.gmd_youtube_play)
                    .color(ThemeUtils.darkTheme ? light : dark)
                    .sizeDp(24);

            playstore = new IconicsDrawable(context)
                    .icon(GoogleMaterial.Icon.gmd_case_play)
                    .color(ThemeUtils.darkTheme ? light : dark)
                    .sizeDp(24);

            website = new IconicsDrawable(context)
                    .icon(GoogleMaterial.Icon.gmd_globe_alt)
                    .color(ThemeUtils.darkTheme ? light : dark)
                    .sizeDp(24);

            bugs = new IconicsDrawable(context)
                    .icon(GoogleMaterial.Icon.gmd_bug)
                    .color(ThemeUtils.darkTheme ? light : dark)
                    .sizeDp(24);

            donate = new IconicsDrawable(context)
                    .icon(GoogleMaterial.Icon.gmd_money_box)
                    .color(ThemeUtils.darkTheme ? light : dark)
                    .sizeDp(24);

            collaboratorsIcon = new IconicsDrawable(context)
                    .icon(GoogleMaterial.Icon.gmd_code)
                    .color(ThemeUtils.darkTheme ? light : dark)
                    .sizeDp(24);

            libs = new IconicsDrawable(context)
                    .icon(GoogleMaterial.Icon.gmd_file_text)
                    .color(ThemeUtils.darkTheme ? light : dark)
                    .sizeDp(24);

            sherryIcon = new IconicsDrawable(context)
                    .icon(GoogleMaterial.Icon.gmd_star)
                    .color(ThemeUtils.darkTheme ? light : dark)
                    .sizeDp(24);

            uiCollaboratorsIcon = new IconicsDrawable(context)
                    .icon(GoogleMaterial.Icon.gmd_palette)
                    .color(ThemeUtils.darkTheme ? light : dark)
                    .sizeDp(24);

            email = new IconicsDrawable(context)
                    .icon(GoogleMaterial.Icon.gmd_email)
                    .color(ThemeUtils.darkTheme ? light : dark)
                    .sizeDp(24);

            iconAuthor.setImageDrawable(person);
            iconDev.setImageDrawable(person);
            iconAuthorGPlus.setImageDrawable(gplus);
            iconAuthorCommunity.setImageDrawable(community);
            youtubeIcon.setImageDrawable(youtube);
            twitterIcon.setImageDrawable(twitter);
            playStoreIcon.setImageDrawable(playstore);
            iconAuthorWebsite.setImageDrawable(website);
            iconDevGitHub.setImageDrawable(github);
            donateIcon.setImageDrawable(donate);
            bugIcon.setImageDrawable(bugs);
            iconAuthorFacebook.setImageDrawable(facebook);
            libsIcon.setImageDrawable(libs);
            collaboratorsIV.setImageDrawable(collaboratorsIcon);
            sherryIV.setImageDrawable(sherryIcon);
            uiCollaboratorsIV.setImageDrawable(uiCollaboratorsIcon);
            iconDevCommunity.setImageDrawable(community);
            emailIV.setImageDrawable(email);
        }

    }

    private void setupExtraAuthorOptions() {
        if (!WITH_ALTERNATIVE_LAYOUT) {
            authorFB.setVisibility(withLinkToFacebook ? View.VISIBLE : View.GONE);
            authorTwitter.setVisibility(withLinkToTwitter ? View.VISIBLE : View.GONE);
            authorGPlus.setVisibility(withLinkToGPlus ? View.VISIBLE : View.GONE);
            authorYouTube.setVisibility(withLinkToYouTube ? View.VISIBLE : View.GONE);
            authorCommunity.setVisibility(withLinkToCommunity ? View.VISIBLE : View.GONE);
            authorPlayStore.setVisibility(withLinkToPlayStore ? View.VISIBLE : View.GONE);
            authorWebsite.setVisibility(withLinkToWebsite ? View.VISIBLE : View.GONE);
        }
    }

    private void showSherryDialog() {
        new MaterialDialog.Builder(context)
                .title(R.string.sherry_title)
                .content(R.string.sherry_dialog)
                .neutralText(R.string.follow_her)
                .positiveText(R.string.close)
                .onNeutral(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog dialog, DialogAction which) {
                        Utils.openLinkInChromeCustomTab(context,
                                getResources().getString(R.string.sherry_link));
                    }
                })
                .show();
    }

    private void showUICollaboratorsDialog() {
        new MaterialDialog.Builder(context)
                .title(R.string.ui_design)
                .negativeText(R.string.close)
                .items(R.array.ui_collaborators_names)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog materialDialog, View view,
                                            final int i, CharSequence charSequence) {
                        Utils.openLinkInChromeCustomTab(context, uiCollaboratorsLinks[i]);
                    }
                }).show();
    }

    private void showLibrariesDialog() {
        new MaterialDialog.Builder(context)
                .title(R.string.implemented_libraries)
                .negativeText(R.string.close)
                .items(R.array.libs_names)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog materialDialog, View view,
                                            final int i, CharSequence charSequence) {
                        Utils.openLinkInChromeCustomTab(context, libsLinks[i]);
                    }
                }).show();
    }

    private void showContributorsDialog() {
        new MaterialDialog.Builder(context)
                .title(R.string.contributors)
                .negativeText(R.string.close)
                .items(R.array.contributors_names)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog materialDialog, View view,
                                            final int i, CharSequence charSequence) {
                        Utils.openLinkInChromeCustomTab(context, contributorsLinks[i]);
                    }
                }).show();
    }

}